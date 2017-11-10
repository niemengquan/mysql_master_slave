package nmq.test;

import com.nmq.mysql.Application;
import com.nmq.mysql.entity.TbCart;
import com.nmq.mysql.service.ITbCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by niemengquan on 2017/11/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Import(Application.class)
public class TbCartServiceTest {
    @Autowired
    private ITbCartService tbCartService;

    @Test
    public void test(){
       /* List<TbCart> all = tbCartService.getAll(1,10);
        System.out.println(all);*/
    }
}
