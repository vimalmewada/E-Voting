package com.evoting.utility;

public class SqlQueries {
	
	public static final String INSERT_VOTER_QUERY = "Insert into votors (name,email,dob,address,password,mobile,is_active) values(?,?,?,?,?,?,?)";
	public static final String VOTER_LOGIN_CHECK_QUERY = "select * from votors where (email = ? OR mobile = ?) AND password = ? and is_active=1 limit 1";
	public static final String EMAIL_DUPLICATE_CHECK_QUERY = "select count(voter_id) as count from votors where email=?";
	public static final String ADMIN_LOGIN_CHECK_QUERY = "select * from adminlogin where (email=? OR mobile=?) and password=?";
	public static final String INSERT_CREATE_ELECTION_QUERY = "Insert into election (election_name,nomination_start_date,nomination_end_date,election_date,result_date,description) values(?,?,?,?,?,?)";
	public static final String ELECTION_QUERY = "select * from election";
	public static final String ELECTION_QUERY_BY_ID = "select * from election where election_id=?";
	public static final String DELETE_ELECTION_BY_ID = "delete from election where election_id=?";
	public static final String UPDATE_ELECTION_QUERY = "update election set election_name=? ,nomination_start_date=?,nomination_end_date=?,election_date=?,result_date=?,description=? where election_id=? ";
	public static final String VOTER_LIST_QUERY = "select * from votors";
	public static final String VOTER_QUERY_BY_ID = "select * from votors where voter_id=?";
	public static final String UPDATE_VOTER_QUERY = "update votors set name=? ,email=?,mobile=?,address=?,dob=? where voter_id=? ";
	public static final String APPROVE_VOTER_QUERY = "update votors set is_active=true where voter_id=?";
	public static final String INSERT_CANDIDATE_QUERY = "INSERT INTO candidate (election_id,name,email,address,mobile,dob,password) values(?,?,?,?,?,?,?)";
	public static final String EMAIL_DUPLICATE_CANDIDATE_CHECK_QUERY = "select count(candidate_id) as count from candidate where email=?";
	public static final String CANDIDATE_LOGIN_CHECK_QUERY = "select * from candidate where (email = ? OR mobile = ?) AND password = ? and is_active=1 limit 1";
	public static final String CANDIDATE_BY_ELECTION_ID_QUERY = " select * from candidate where election_id=?";
	public static final String INSERT_VOTE_QUERY = "insert into vote (election_id,candidate_id,voter_id) values(?,?,?)";
	public static final String DUPLICATE_VOTE_QUERY = "Select * from vote where election_id=? AND voter_id=? ";
	public static final String CANDIDATE_LIST_QUERY = "SELECT * FROM CANDIDATE";
	public static final String ACTIVE_VOTER_QUERY_BY_ID = "select * from votors where voter_id=? AND is_active=?";
	public static final String CANDIDATE_QUERY_BY_ID = "select * from candidate where candidate_id=?";
	public static final String UPDATE_CANDIDATE_QUERY = "update candidate set name=?,email=?,mobile=?,address=?,dob=? where candidate_id=?";
	public static final String ACTIVE_CANDIDATE_BY_QUERY = "update candidate set is_active=true where candidate_id=?";
	public static final String RESULT_DECLARE_BY_ID = "update election set is_result=true where election_id=?";
	public static final String RESULT_OUT_QUERY="Select can.name, count(vt.voting_store_id) as vote_count ,vt.election_id, vt.candidate_id, el.election_name from vote as vt join candidate as can on \r\n"
			+ "	  vt.candidate_id= can.candidate_id  join election as el on el.election_id=vt.election_id   where el.result_date<=? and   el.is_result=? Group by vt.candidate_id, vt.election_id;\r\n"
			+ "	  ";
//select  candidate_id,count(candidate_id) from vote where election_id="9" group by candidate_id order by candidate_id asc;
//select election_id,candidate_id,count(*) as votes from vote group by election_id having count(*)>0 ;
	  //Select can.name, count(vt.voting_store_id) as vote_count ,vt.election_id, vt.candidate_id, el.election_name from vote as vt join candidate as can on 
	// vt.candidate_id= can.candidate_id  join election as el on el.election_id=vt.election_id   where vt.election_id = 9 Group by vt.candidate_id, vt.election_id;

	  
}
