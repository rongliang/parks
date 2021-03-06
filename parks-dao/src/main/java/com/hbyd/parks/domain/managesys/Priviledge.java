package com.hbyd.parks.domain.managesys;


// Generated 2014-6-18 13:23:23 by Hibernate Tools 3.4.0.CR1

import com.hbyd.parks.common.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**当前类描述：主体对资源有什么权限
 * @author ren_xt
 *
 */
@Entity
@Table(name = "base_priviledge")
public class Priviledge extends BaseEntity {
	
	/**
	 * 主体类型
	 */
	private String priOwnerType;
	/**
	 * 资源类型
	 */
	private String priResType;
	
	/**
	 * 主体ID
	 */
	private String priOwnerId;
	/**
	 * 资源 ID
	 */
	private String priResId;

    /**
     * url
     */
    private String url;
	

	public Priviledge() {
	}

    public String getPriOwnerType() {
        return priOwnerType;
    }

    public void setPriOwnerType(String priOwnerType) {
        this.priOwnerType = priOwnerType;
    }

    public String getPriResType() {
        return priResType;
    }

    public void setPriResType(String priResType) {
        this.priResType = priResType;
    }

    public String getPriOwnerId() {
        return priOwnerId;
    }

    public void setPriOwnerId(String priOwnerId) {
        this.priOwnerId = priOwnerId;
    }

    public String getPriResId() {
        return priResId;
    }

    public void setPriResId(String priResId) {
        this.priResId = priResId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
