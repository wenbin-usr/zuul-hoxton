package edu.whpu.controller;

import edu.whpu.entity.Order;
import edu.whpu.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author bin_wen
 * @date 2025/2/7 00:24
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        Order order = new Order();
        // 远程调用，ResponseEntity: 封装了返回数据
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                "http://product-service/product/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {});
        List<Product> productList = response.getBody();
        order.setProductList(productList);
        order.setId(id);
        return order;
    }

//    @GetMapping("/{id}")
//    public Order getById(@PathVariable Long id) {
//        Order order = new Order();
//        StringBuffer sb = new StringBuffer();
//        List<Product> productList;
//        // 获取服务列表
//        List<String> serviceIds = discoveryClient.getServices();
//        // 根据服务名称获取服务
//        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("product-service");
//        if (!CollectionUtils.isEmpty(serviceInstances)) {
//            // 选择第一个服务实例
//            ServiceInstance si = serviceInstances.get(0);
//            // 获取该实例的IP和端口，拼接成
//            sb.append("http://" + si.getHost() + ":" + si.getPort() + "/product/list");
//        }
//        // ResponseEntity: 封装了返回数据
//        ResponseEntity<List<Product>> response = restTemplate.exchange(
//                sb.toString(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Product>>() {});
//        productList = response.getBody();
//        order.setProductList(productList);
//        order.setId(id);
//        return order;
//    }

//    @GetMapping("/{id}")
//    public Order getById(@PathVariable Long id) {
//        Order order = new Order();
//        StringBuffer sb = new StringBuffer();
//        List<Product> productList;
//        // 根据服务名称获取服务
//        ServiceInstance si = loadBalancerClient.choose("product-service");
//        if (si != null) {
//            sb.append("http://" + si.getHost() + ":" + si.getPort() + "/product/list");
//        }
//        // ResponseEntity: 封装了返回数据
//        ResponseEntity<List<Product>> response = restTemplate.exchange(
//                sb.toString(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Product>>() {});
//        productList = response.getBody();
//        order.setProductList(productList);
//        order.setId(id);
//        return order;
//    }
}
