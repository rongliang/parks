package com.hbyd.parks.ws.managesys;

import com.hbyd.parks.common.base.BaseWS;
import com.hbyd.parks.dto.managesys.ResBtnDTO;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.BindingType;

/**
 * Created by allbutone on 14-7-21.
 */
@WebService
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
@XmlSeeAlso({ResBtnDTO.class})
public interface ResBtnWS extends BaseWS<ResBtnDTO> {
}
