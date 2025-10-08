package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.service.ProductService;



@Controller
public class OrderController {

    public OrderController(vn.hoidanit.laptopshop.service.ProductService productService) {
        this.productService = productService;
    }
    private final ProductService productService;

    @GetMapping("/admin/order")
    public String getOrderPage(Model model) {
        List<Order> orders = this.productService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getDetailOrderPage(Model model, @PathVariable("id") Long id) {
        Order order = this.productService.getOrderById(id);
        List<OrderDetail> orderDetails = this.productService.getOrderDetailByOrder(order);
        model.addAttribute("id", id);
        model.addAttribute("orderDetails", orderDetails);
        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdateOrderPage(Model model, @PathVariable("id") Long id) {
        Order order = this.productService.getOrderById(id);
        model.addAttribute("order", order);
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String postUpdateOrder(@ModelAttribute("order") Order order) {
        Order updateOrder = this.productService.getOrderById(order.getId());
        updateOrder.setStatus(order.getStatus());
        this.productService.saveOrder(updateOrder);
        return "redirect:/admin/order";
    }


    @GetMapping("/admin/order/delete/{id}")
    public String getDeleteOrderPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("order", this.productService.getOrderById(id));
        model.addAttribute("id", id);
        return "admin/order/delete";
    }
    
    @PostMapping("/admin/order/delete")
    public String postDeleteOrder(@ModelAttribute("order") Order order) {
        Order deleteOrder = this.productService.getOrderById(order.getId());
        this.productService.deleteOrder(deleteOrder);
        return "redirect:/admin/order";
    }
    
}
