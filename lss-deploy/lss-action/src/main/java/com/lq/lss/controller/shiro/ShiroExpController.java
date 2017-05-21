/**
 *
 */
package com.lq.lss.controller.shiro;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.view.AjaxModelAndViewUtils;
import com.lq.util.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lanbo
 */
@Controller
@RequestMapping(value = "/shiro/error.htm")
public class ShiroExpController {

    @RequestMapping
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        ResultDto resultDto = new ResultDto();
        resultDto.setSuccess(false);
        resultDto.setErrorMsg("无操作权限，请联系系统管理");
        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
    }


}
