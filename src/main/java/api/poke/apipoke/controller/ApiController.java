package api.poke.apipoke.controller;

import api.poke.apipoke.service.ImageService;
import api.poke.apipoke.utils.R;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    ImageService imageService;

    private final Logger log = LoggerFactory.getLogger(ApiController.class);
    private final String[] apis = new String[]{"jh", "kd", "zyy", "psj", "mb", "ik", "diu", "gs", "nz", "ff", "tui", "jb", "lh", "tun"};

    @RequestMapping(value = "/poke")
    public void poke(ServletRequest request, ServletResponse response) throws IOException {
        String qq = request.getParameter("qq");
        String m = request.getParameter("m");
        String api;
        if (qq == null) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSONObject.toJSONString(R.NullQQ()));
            return;
        }
        long start = new Date().getTime();
        if (m == null) api = apis[(int) (Math.random() * apis.length)];
        else api = m;
        log.info(String.format("收到请求 from: %s，开始任务：[%s]", qq, api));
        try {
            ByteArrayOutputStream img = null;
            switch (api) {
                case "kd":
                    img = imageService.kd(qq);
                    break;
                case "zyy":
                    img = imageService.zyy(qq);
                    break;
                case "psj":
                    img = imageService.psj(qq);
                    break;
                case "mb":
                    img = imageService.mb(qq);
                    break;
                case "ik":
                    img = imageService.iKun(qq);
                    break;
                case "diu":
                    img = imageService.diu(qq);
                    break;
                case "gs":
                    img = imageService.gs(qq);
                    break;
                case "nz":
                    img = imageService.nz(qq);
                    break;
                case "ff":
                    img = imageService.ff(qq);
                    break;
                case "tui":
                    img = imageService.tui(qq);
                    break;
                case "jb":
                    img = imageService.jb(qq);
                    break;
                case "lh":
                    img = imageService.lh(qq);
                    break;
                case "tun":
                    img = imageService.tun(qq);
                    break;
                case "jh":
                    img = imageService.jh(qq);
                    break;
                default:
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().print(JSONObject.toJSONString(R.UNAVAILABLEMethod()));
                    return;
            }
            response.setContentType("image/gif");
            OutputStream out = response.getOutputStream();
            out.write(img.toByteArray());
            out.flush();
            out.close();
        } catch (Exception err) {
            if (err instanceof MalformedURLException || err instanceof ProtocolException) {
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print(JSONObject.toJSONString(R.AVATARError()));
                return;
            }
            System.out.println(err);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSONObject.toJSONString(R.OTHERError()));
        }
        log.info(String.format("处理完成：[%s] from(%s) --> 用时%sms", api, qq, new Date().getTime() - start));
    }
}
