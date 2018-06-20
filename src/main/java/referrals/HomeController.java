package referrals;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String index() {
       
        return  getEmployees();
    }


    private static String getEmployees()
    {
        final String uri = "https://raw.githubusercontent.com/Nearsoft/jobs/master/readme.md";
        
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return(result);
    }

}