package cn.net.liaowei.sc.product.repository;

import cn.net.liaowei.sc.product.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LiaoWei
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    /**
     * 通过类别编号列表查询编号列表对应的类别信息列表
     * @param categoryIdList 类别编号列表
     * @return 类别信息列表
     */
    List<ProductCategory> findByCategoryIdIn(List<Integer> categoryIdList);
}
