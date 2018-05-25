package thupnm.project.webrest;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import thupnm.project.dto.CategoryDTO;
import thupnm.project.dto.ProductDTO;
import thupnm.project.service.ProductService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class ProductController implements ProductApi {

    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<Page<ProductDTO>> getAll(@Min(value = 0) @RequestParam Integer pageNumber,
                                                   @Max(value = 50) @RequestParam Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
        Page<ProductDTO> productDTOS = productService.getAll(pageRequest);
        return ResponseEntity.ok(productDTOS);
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> getCategory() {
        List<CategoryDTO> categoryDTOS = productService.getCategory();
        return ResponseEntity.ok(categoryDTOS);
    }
}
