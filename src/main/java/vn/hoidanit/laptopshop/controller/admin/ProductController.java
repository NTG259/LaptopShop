package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UpLoadService;






@Controller
public class ProductController {
    private final UpLoadService upLoadService;
    private final ProductService productService;

    public ProductController(ProductService productService, UpLoadService upLoadService) {
        this.productService = productService;
        this.upLoadService = upLoadService;
    }

    @GetMapping("/admin/product")
    public String getProduct(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product/show";
    }
    
    @GetMapping("/admin/product/create")
    public String getCreateProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }


    @PostMapping("/admin/product/create")
    public String createProduct(
        Model model, 
        @ModelAttribute("newProduct") @Valid Product newProduct, 
        BindingResult newProductBindingResult, 
        @RequestParam("productImgFile") MultipartFile file ) {
        
        List<FieldError> errors = newProductBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>>" + error.getField() + " - " + error.getDefaultMessage());
        }

        if (newProductBindingResult.hasErrors()) {
            return "admin/product/create";
        }

        String img = this.upLoadService.handleSaveUploadFile(file, "product");
        newProduct.setImage(img);
        this.productService.handleSaveProduct(newProduct);
        return "redirect:/admin/product";
    }
    
    @GetMapping("/admin/product/{id}")
    public String getDetailProduct(Model model, @PathVariable("id") Long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("detailProduct", product);
        return "admin/product/detail";
    }
    

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable("id") Long id) {
        Product updateProduct = this.productService.getProductById(id);
        model.addAttribute("product", updateProduct);
        return "admin/product/update";
    }
    

    @PostMapping("/admin/product/update")
    public String postUpdateProduct(
        Model model,
        @ModelAttribute("product") @Valid Product product, 
        BindingResult newProductBindingResult,
        @RequestParam("updateImgFile") MultipartFile file) {
        // --Validate data


        if (newProductBindingResult.hasErrors()) {
            return "admin/product/update";
        }
        
        Product currentProduct = this.productService.getProductById(product.getId());
        if (currentProduct != null) {
            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setShortDesc(product.getShortDesc());
            currentProduct.setDetailDesc(product.getDetailDesc());
            currentProduct.setFactory(product.getFactory());
            currentProduct.setSold(product.getSold());
            currentProduct.setTarget(product.getTarget());
            if (!file .isEmpty()){
                String img = this.upLoadService.handleSaveUploadFile(file, "product");
                currentProduct.setImage(img);
            }
            this.productService.handleSaveProduct(currentProduct);
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(Model model, @PathVariable("id") long id) {
        model.addAttribute("id", id);
        model.addAttribute("product", new Product());
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteProduct(Model model, @ModelAttribute("product") Product product) {
        this.productService.deleteProductById(product.getId());
        return "redirect:/admin/product";
    }
    
    
}
