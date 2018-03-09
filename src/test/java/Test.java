import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Test {


    @org.junit.Test
    public void waiting(){
        List<String> x = new ArrayList<>();
        x.add("jiang");
        x.add("xiao");
        x.add("lin");

        System.out.println(StringUtils.join(x,","));
    }
}
