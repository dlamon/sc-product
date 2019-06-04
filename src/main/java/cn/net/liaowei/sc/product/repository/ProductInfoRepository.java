package cn.net.liaowei.sc.product.repository;

import cn.net.liaowei.sc.product.domain.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {
    /**
     * 通过状态查询所有产品信息(测试使用)
     * @param status 产品状态
     * @return 产品信息
     */
    List<ProductInfo> findByStatus(Short status);

    /**
     * 通过状态分页查询所有产品信息(测试使用)
     * @param status 产品状态
     * @param pageable 分页参数
     * @return 产品信息
     */
    @Query("select productName from ProductInfo where status = :status")
    Page<String> findByStatusQuery(@Param("status") Short status, Pageable pageable);

    /**
     * 通过状态分页查询所有产品信息
     * @param status 产品状态
     * @param pageable 分页参数
     * @return 产品信息
     */
    Page<ProductInfo> findByStatus(Short status, Pageable pageable);

    /**
     * 通过产品ID列表查询ID所属的产品信息列表
     * @param productIdList 产品ID列表
     * @param pageable 分页参数
     * @return 产品信息列表
     */
    Page<ProductInfo> findByProductIdIn(List<Integer> productIdList, Pageable pageable);
}
