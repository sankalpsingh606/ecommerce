alert("loaded")


function add_to_cart(pid,pname,price,quantity) {
	let cart =localStorage.getItem("cart");
	if(cart == null){
		// no cart yet
		let products=[];
		let product={productId:pid,productName:pname,productQuantity:1,productPrice:price};
		products.push(product);
		localStorage.setItem("cart",JSON.stringify(products));
		console.log("frist Product are added");
		showToast(" item is added to cart");
	}
	
	else {
		// cart is already present
		let pcart=JSON.parse(cart);
		let oldProduct=pcart.find((item) => item.productId==pid)
		if(oldProduct){
			// we have to increase the quantity
			if(oldProduct.productQuantity < quantity)
			{
			oldProduct.productQuantity=oldProduct.productQuantity+1;
			
			pcart.map((item) =>{
				if (item.productId == oldProduct.productId) {
					item.productQuantity=oldProduct.productQuantity;
				}
			})
			localStorage.setItem("cart",JSON.stringify(pcart));
			console.log("Increase Product are Quantity");
			showToast(oldProduct.productName+" quantity is Increase , Quantity : "+oldProduct.productQuantity);
			//showToast(oldProduct.productName+" Increase Product are Quantity );
			}
			else {
				console.log("NOt Increase Quantity");	
			showToast("Quantity are not Increase this time stock are not aviable");
			}
			
			}
					else {
			// we have add the product
			let product={productId:pid,productName:pname,productQuantity:1,productPrice:price};
			pcart.push(product);
			localStorage.setItem("cart",JSON.stringify(pcart));
			console.log("Product are added");
			showToast("Product is added to cart");
		}
	}
	updateCart();
}

// update Cart
function updateCart() {
	let cartString =localStorage.getItem("cart");
	let cart=JSON.parse(cartString);
	if (cart == null || cart.length == 0) {
		console.log("Cart is empty !!");
		localStorage.clear();
	$(".cart-items").html("(0)");
	$(".cart-body").html("<h3>Cart does not have any item</h3>");
	$(".checkout-btn").attr('disabled',true);
	}
	else {
		// there is some in cart to show
		console.log(cart);
		$(".cart-items").html(`(${cart.length})`);
		let table=`
		<table class="table">
  <thead>
    <tr>
      <th scope="col">Item Name</th>
      <th scope="col">Price </th>
      <th scope="col">Quantity</th>
      <th scope="col">Total Price</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  		
		`;
	let totalprice = 0;
		cart.map((item)=>{
			table+=`
			<tbody>
    <tr>
      
      <td>${item.productName}</td>
      <td>${item.productPrice}</td>
      <td>${item.productQuantity}</td>
      <td>${item.productQuantity*item.productPrice}</td>
      <td><button onclick="deleteProduct(${item.productId})" type="button" class="btn btn-danger btn-sm">Remove</button></td>
    </tr>`
		
				totalprice+=item.productPrice*item.productQuantity;
		})
		table=table +`
		<tr><td colspan='5' class='text-right font-weight-bold'> Total Price : ${totalprice}</td></tr>
		</tbody></table>`
		$(".cart-body").html(table);
		$(".checkout-btn").attr('disabled',false);
	}
}

function deleteProduct(pid) {
	let cart =JSON.parse(localStorage.getItem("cart"));
	let newcart=cart.filter((item) => item.productId != pid );
	localStorage.setItem("cart",JSON.stringify(newcart));
	
updateCart();
showToast("item is removed form Cart");
}

$(document).ready(function(){
	
	updateCart();
})

function showToast(content) {
	
	$("#toast").addClass("display");
	$("#toast").html(content);
	setTimeout(() => {
		$("#toast").removeClass("display");
	}, 2000);
}
function gotoCheckout() {
	window.location='checkout.jsp';
	
}