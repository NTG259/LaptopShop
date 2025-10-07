package vn.hoidanit.laptopshop.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.ProductService;


@Controller
public class ItemController {

    private final ProductService productService;
    
    public ItemController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getDetailProduct(Model model, @PathVariable("id") long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long productId = id;
        String email = (String) session.getAttribute("email");
        this.productService.handleAddProductToCart(email, productId, session);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCartDetail(Model model, HttpServletRequest request) {
        User currentUser = new User();
        HttpSession session = request.getSession(false);
        Long id = (Long) session.getAttribute("id");
        currentUser.setId(id);
        Cart cart = this.productService.findByUser(currentUser);
        List<CartDetail> cartDetails = (cart == null ? new ArrayList<>() : cart.getCartDetails());
        double totalPrice = 0F;
        for (CartDetail item : cartDetails) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("cart", cart);
        return "client/cart/show";
    }

    @PostMapping("/delete-cart-product/{id}")
    public String postDeleteItemInCart(Model model, @PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        this.productService.handleRemoveCartDetail(id, session);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String getCheckoutPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = new User();
        user.setId((Long)session.getAttribute("id"));
        Cart cart = this.productService.findByUser(user);
        model.addAttribute("cartDetails", cart.getCartDetails());
        double totalPrice = 0;
        for (CartDetail cd : cart.getCartDetails()) {
            totalPrice += cd.getPrice() * cd.getQuantity();
        }
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", new Cart());
        return "client/cart/checkout";
    }
    
    
    @PostMapping("/confirm-checkout")
    public String getCheckOutPage(@ModelAttribute("cart") Cart cart) {
        // Kiểm tra nếu cart là null, khởi tạo một danh sách rỗng
        List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();

        // Xử lý cập nhật giỏ hàng trước khi thanh toán, lưu lại số lượng vào database
        this.productService.handleUpdateCartBeforeCheckout(cartDetails);

        //Chuyển hướng đến trang thanh toán
        return "redirect:/checkout";
    }

    @PostMapping("/place-order")
    public String handlePlaceOrder(
        HttpServletRequest request,
        @RequestParam("receiverName") String receiverName,
        @RequestParam("receiverAddress") String receiverAddress,
        @RequestParam("receiverPhone") String receiverPhone
    ) {
        HttpSession session = request.getSession(false);
        User currentUser = new User();
        currentUser.setId((Long)session.getAttribute("id"));

        this.productService.handlePlaceOrder(currentUser, session, receiverName, receiverAddress, receiverPhone);
        return "client/cart/thanks";
    }
}
