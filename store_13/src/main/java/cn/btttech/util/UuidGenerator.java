package cn.btttech.util;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UuidGenerator{

	public static String generate(){
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String[] subStr = str.split("-");
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < subStr.length; i++) {
			buff.append(subStr[i].toString());
		}
		return buff.toString();
	}

}
