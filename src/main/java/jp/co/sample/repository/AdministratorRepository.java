package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

@Repository

public class AdministratorRepository {

	@Autowired

	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = new BeanPropertyRowMapper<>(
			Administrator.class);

	/**
	 * @param administrator 管理者情報
	 * @param mailAddress   メールアドレス
	 * @param password      パスワード
	 * @return Administrator (メールアドレスとパスワードから管理者情報を取得)
	 * 
	 * 
	 */

	public void save(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String insertSql = "INSERT INTO administrator(name,mail_address,password)"
				+ "VALUES(:name,:mailAddress,:password)";
	}

	public Administrator findBy(String mailAddress, String password) {
		String findSql = "SELECT * FROM administrators WHERE mail_address=:mailAddress AND password=:password;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",
				password);
		List<Administrator> administratorList = template.query(findSql, param, ADMINISTRATOR_ROW_MAPPER);
		if (administratorList.size() == 0) {
			return null;
		}

		return administratorList.get(0);

	}

}
