package me.own.learn.test.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * 使用spring-test组件MockMvc以及测试IOC bean的基类
 * 但由于spring配置项采用了相对路径来定位文件，所以此处缺失
 * 子类必须在自己的实现中，根据自身与mvc-dispatcher-servlet-test.xml文件的位置关系自行重写，如：
 * “@ContextConfiguration(locations = {"file:../../../learn-api/src/main/webapp/WEB-INF/mvc-dispatcher-servlet-test.xml"})”
 * 也可以在此处统一配置spring application content.xml的绝对路径以省略每个子类单独定义路径，但此路径会因为个人的代码位置而不同，如：
 * “@ContextConfiguration(locations = {"file:F:\gitRepository\webook\Source\Trunk\lishu\lishu-api\src\main\webapp\WEB-INF\mvc-dispatcher-servlet-test.xml"})”
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:mvc-dispatcher-servlet-test.xml"
//        "file:../../../learn-api/src/test/resources/mvc-dispatcher-servlet-test.xml"
})
//@Transactional
//@Rollback
public abstract class BaseUnitTest {

    /***
     * Admin authentication token
     *
     * The subclass test should get token by itself if auth required
     */
    protected static String adminToken;
    /***
     * Customer authentication token
     */
    protected static String customerToken;
    protected ObjectMapper objectMapper = new ObjectMapper();
    protected MockMvc mockMvc;

    /***
     * Convert an object to request body json string
     * @param o object to convert
     * @return a json string
     * @throws JsonProcessingException
     */
    protected String convertToRequestBody(Object o) throws JsonProcessingException {
        ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(o);
    }
}
