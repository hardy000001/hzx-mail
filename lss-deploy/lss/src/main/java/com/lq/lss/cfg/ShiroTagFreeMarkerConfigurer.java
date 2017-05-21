package com.lq.lss.cfg;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/** freemarker 集成shiro tags 。
 * Created by lanbo on 16/11/8.
 */


public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer {

    @Override

    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }

}