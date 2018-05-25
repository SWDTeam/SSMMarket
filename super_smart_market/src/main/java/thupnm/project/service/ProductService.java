package thupnm.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import thupnm.project.dto.CategoryDTO;
import thupnm.project.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    /**
     * @return pageable
     */
    Page<ProductDTO> getAll(Pageable pageable);

    /**
     *
     * @return
     */
    List<CategoryDTO> getCategory();
}
