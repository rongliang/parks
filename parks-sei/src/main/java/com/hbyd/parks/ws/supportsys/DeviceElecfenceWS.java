package com.hbyd.parks.ws.supportsys;

import com.hbyd.parks.common.base.BaseWS;
import com.hbyd.parks.common.base.RecoverableWS;
import com.hbyd.parks.dto.supportsys.DeviceElecfenceDTO;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * WS 接口：
 */
@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
@XmlSeeAlso({DeviceElecfenceDTO.class})
public interface DeviceElecfenceWS extends BaseWS<DeviceElecfenceDTO>, RecoverableWS {
}
