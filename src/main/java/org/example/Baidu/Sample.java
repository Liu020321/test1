package org.example.Baidu;

import java.util.*;
import java.awt.*;
import java.io.*;
import java.util.List;

import com.baidu.aip.imageclassify.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Sample {
    // 设置APPID/AK/SK
    private static String APP_ID;
    private static String API_KEY;
    private static String SECRET_KEY;
    private static Component parent;

    static {
        // 读取baidu.properties文件的内容
        InputStream is = Sample.class.getClassLoader().getResourceAsStream("baidu.properties");
        Properties p = new Properties();
        try {
            p.load(is);
            APP_ID = p.getProperty("APP_ID");
            API_KEY = p.getProperty("API_KEY");
            SECRET_KEY = p.getProperty("SECRET_KEY");
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }


    public static String images(String name) {
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        // client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
        // client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理
        // 调用接口
//    String path = "D:\\Study\\Code\\Java\\IDEA\\rjgz\\test1\\src\\main\\images\\cat.jpg";
//    JSONObject res = client.objectDetect(path, new HashMap<String, String>());
//    System.out.println(res.toString(2));

        HashMap<String, Object> options = new HashMap<String, Object>();

        List<String> scenes = new ArrayList<String>();
        scenes.add("animal");
//        scenes.add("plant");
//        scenes.add("advanced_general");
//        scenes.add("object_detect");
//        scenes.add("multi_object_detect");
//        scenes.add("logo_search");
//        scenes.add("ingredient");
//        scenes.add("dish_search");
//        scenes.add("dishs");
//        scenes.add("red_wine");
//        scenes.add("currency");
//        scenes.add("landmark");
        // 参数为本地路径
//        String image = "D:\\Study\\Code\\Java\\IDEA\\rjgz\\test1\\src\\main\\images\\lanzi.jpg";


        JSONObject res = client.combinationByImage(name, scenes, options);
        System.out.println(res.toString(2));
        JSONObject result = res.getJSONObject("result");
        JSONObject animal = result.getJSONObject("animal");
        JSONArray result1 = animal.getJSONArray("result");
        JSONObject result0 = (JSONObject) result1.get(0);
        return result0.getString("name");

//            JSONObject result0 = (JSONObject) result.get(0);
//            return result0.getString("keyword");
//        return res.toString(2);
    }


 /*
 {
  "result": {
  "animal": {
    "result": [
      {
        "score": "0.612306",
        "name": "家猫"
      },
      {
        "score": "0.0504854",
        "name": "欧洲短毛猫"
      },
      {
        "score": "0.046312",
        "name": "虎斑猫"
      },
      {
        "score": "0.0360169",
        "name": "美国短毛猫"
      },
      {
        "score": "0.0188969",
        "name": "美国短尾猫"
      },
      {
        "score": "0.0181204",
        "name": "中国狸花猫"
      }
    ],
    "log_id": 1586593470647745082
  }},
  "log_id": 16671083316800348
}


  */


//    public static void main(String[] args) {
//        System.out.println(images("sss"));
//    }
//    }
}