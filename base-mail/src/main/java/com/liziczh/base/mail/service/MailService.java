package com.liziczh.base.mail.service;

import com.liziczh.base.mail.dto.MailSendReq;
import com.liziczh.base.mail.dto.MailSendResult;

public interface MailService {

    /**
     * 发送文本邮件
     *
     * @param request
     * @return com.liziczh.mvc.mesh.ref.mail.dto.MailSendResult
     * @author chenzhehao
     * @date 2022/1/16 7:10 下午
     */
    MailSendResult sendTextMail(MailSendReq request);

    /**
     * 发送MIME邮件
     *
     * @param request
     * @return com.liziczh.mvc.mesh.ref.mail.dto.MailSendResult
     * @author chenzhehao
     * @date 2022/1/16 7:10 下午
     */
    MailSendResult sendMimeMail(MailSendReq request);
}
