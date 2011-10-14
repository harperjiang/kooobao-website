package org.harper.ourwebsite.domain.dao.toplink;

import oracle.toplink.sessions.*;
import oracle.toplink.descriptors.*;
import oracle.toplink.descriptors.invalidation.*;
import oracle.toplink.mappings.*;
import oracle.toplink.mappings.converters.*;
import oracle.toplink.queryframework.*;
import oracle.toplink.expressions.ExpressionBuilder;
import oracle.toplink.history.HistoryPolicy;
import oracle.toplink.sequencing.*;

/**
 * This class was generated by the TopLink project class generator.
 * It stores the meta-data (descriptors) that define the TopLink mappings.
 * ## Oracle TopLink - 10g Release 3 (10.1.3.4.0) (Build 080602) ##
 * @see oracle.toplink.tools.workbench.ProjectClassGenerator
 */

public class OurwebsiteProject extends oracle.toplink.sessions.Project {

public OurwebsiteProject() {
	setName("ourwebsite");
	applyLogin();
	
	addDescriptor(buildCommonDaoImpl$NumGenDescriptor());
	addDescriptor(buildUserDescriptor());
}

public void applyLogin() {
	DatabaseLogin login = new DatabaseLogin();
	login.usePlatform(new oracle.toplink.platform.database.MySQL4Platform());
	login.setDriverClassName("com.mysql.jdbc.Driver");
	login.setConnectionString("jdbc:mysql://localhost:3306/ourwebsite");
	login.setUserName("root");
	login.setEncryptedPassword("BF06934E3F151B148BEEB6319A4856D6");
	
	// Configuration Properties.
	
	// Sequencing.
	login.setDefaultSequence(new TableSequence("", 50, "sys_sequence", "table_name", "seq_count"));
	
	setDatasourceLogin(login);
}

public ClassDescriptor buildCommonDaoImpl$NumGenDescriptor() {
	RelationalDescriptor descriptor = new RelationalDescriptor();
	descriptor.setJavaClass(org.harper.ourwebsite.domain.dao.toplink.CommonDaoImpl.NumGen.class);
	descriptor.addTableName("sys_num_gen");
	descriptor.addPrimaryKeyFieldName("sys_num_gen.type");
	
	// Descriptor Properties.
	descriptor.useSoftCacheWeakIdentityMap();
	descriptor.setIdentityMapSize(100);
	descriptor.useRemoteSoftCacheWeakIdentityMap();
	descriptor.setRemoteIdentityMapSize(100);
	descriptor.setAlias("CommonDaoImpl$NumGen");
	
	
	// Query Manager.
	descriptor.getQueryManager().checkCacheForDoesExist();
	
	
	// Event Manager.
	
	// Mappings.
	DirectToFieldMapping currentMapping = new DirectToFieldMapping();
	currentMapping.setAttributeName("current");
	currentMapping.setFieldName("sys_num_gen.current_no");
	descriptor.addMapping(currentMapping);
	
	DirectToFieldMapping lengthMapping = new DirectToFieldMapping();
	lengthMapping.setAttributeName("length");
	lengthMapping.setFieldName("sys_num_gen.length");
	descriptor.addMapping(lengthMapping);
	
	DirectToFieldMapping prefixMapping = new DirectToFieldMapping();
	prefixMapping.setAttributeName("prefix");
	prefixMapping.setFieldName("sys_num_gen.prefix");
	descriptor.addMapping(prefixMapping);
	
	DirectToFieldMapping suffixMapping = new DirectToFieldMapping();
	suffixMapping.setAttributeName("suffix");
	suffixMapping.setFieldName("sys_num_gen.suffix");
	descriptor.addMapping(suffixMapping);
	
	DirectToFieldMapping typeMapping = new DirectToFieldMapping();
	typeMapping.setAttributeName("type");
	typeMapping.setFieldName("sys_num_gen.type");
	descriptor.addMapping(typeMapping);
	
	return descriptor;
}

public ClassDescriptor buildUserDescriptor() {
	RelationalDescriptor descriptor = new RelationalDescriptor();
	descriptor.setJavaClass(org.harper.ourwebsite.domain.entity.profile.User.class);
	descriptor.addTableName("pf_user");
	descriptor.addPrimaryKeyFieldName("pf_user.oid");
	
	// Descriptor Properties.
	descriptor.useSoftCacheWeakIdentityMap();
	descriptor.setIdentityMapSize(100);
	descriptor.useRemoteSoftCacheWeakIdentityMap();
	descriptor.setRemoteIdentityMapSize(100);
	descriptor.setSequenceNumberFieldName("pf_user.oid");
	descriptor.setSequenceNumberName("pf_user");
	descriptor.setAlias("User");
	
	
	// Query Manager.
	descriptor.getQueryManager().checkCacheForDoesExist();
	
	
	// Event Manager.
	
	// Mappings.
	DirectToFieldMapping emailMapping = new DirectToFieldMapping();
	emailMapping.setAttributeName("email");
	emailMapping.setFieldName("pf_user.email");
	descriptor.addMapping(emailMapping);
	
	DirectToFieldMapping idMapping = new DirectToFieldMapping();
	idMapping.setAttributeName("id");
	idMapping.setFieldName("pf_user.id");
	descriptor.addMapping(idMapping);
	
	DirectToFieldMapping nameMapping = new DirectToFieldMapping();
	nameMapping.setAttributeName("name");
	nameMapping.setFieldName("pf_user.name");
	descriptor.addMapping(nameMapping);
	
	DirectToFieldMapping oidMapping = new DirectToFieldMapping();
	oidMapping.setAttributeName("oid");
	oidMapping.setFieldName("pf_user.oid");
	descriptor.addMapping(oidMapping);
	
	DirectToFieldMapping passwordMD5Mapping = new DirectToFieldMapping();
	passwordMD5Mapping.setAttributeName("passwordMD5");
	passwordMD5Mapping.setFieldName("pf_user.password_md5");
	descriptor.addMapping(passwordMD5Mapping);
	
	DirectToFieldMapping phoneMapping = new DirectToFieldMapping();
	phoneMapping.setAttributeName("phone");
	phoneMapping.setFieldName("pf_user.phone");
	descriptor.addMapping(phoneMapping);
	
	DirectToFieldMapping regcodeMapping = new DirectToFieldMapping();
	regcodeMapping.setAttributeName("regcode");
	regcodeMapping.setFieldName("pf_user.reg_code");
	descriptor.addMapping(regcodeMapping);
	
	DirectToFieldMapping statusMapping = new DirectToFieldMapping();
	statusMapping.setAttributeName("status");
	statusMapping.setFieldName("pf_user.status");
	descriptor.addMapping(statusMapping);
	
	DirectToFieldMapping typeMapping = new DirectToFieldMapping();
	typeMapping.setAttributeName("type");
	typeMapping.setFieldName("pf_user.type");
	descriptor.addMapping(typeMapping);
	
	return descriptor;
}

}