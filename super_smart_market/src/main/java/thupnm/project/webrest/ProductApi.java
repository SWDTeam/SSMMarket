package thupnm.project.webrest;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thupnm.project.dto.CategoryDTO;
import thupnm.project.dto.ProductDTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RequestMapping("/furniture")
public interface ProductApi {

    /**
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "Get All Product", notes = "", response = ResponseEntity.class, tags = {"Product",})
    @GetMapping("")
    ResponseEntity<Page<ProductDTO>> getAll(@Min(value = 0) @RequestParam(value = "page", required = true) Integer pageNumber,
                                            @Max(value = 50) @RequestParam(value = "page", required = true) Integer pageSize);

    @ApiOperation(value = "Get All Categories", notes = "", response = ResponseEntity.class, tags = {"Category",})
    @GetMapping("/getcategory")
    ResponseEntity<List<CategoryDTO>> getCategory();
}
