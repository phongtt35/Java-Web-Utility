/*
 * Thông tin kết nối của Database được khai báo ở class này
 */
public final class DbMetadata {
	
	/// Phần có thể sửa 
	// =======>
	private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456";
    private static final String SERVER = "localhost";
    private static final String PORT = "1433";
    private static final String DATABASE_NAME = "J4_ToDoApp";
    // Trường hợp báo lỗi SSL thì để là true
    private static final boolean USING_SSL = false; 
    // <=========
    /// Hết phần có thể sửa
    
    
    /// Không sửa những phần dưới này
    private static String CONNECT_STRING;
	static {
		StringBuilder connectStringBuilder = new StringBuilder();
        connectStringBuilder.append("jdbc:sqlserver://")
                .append(SERVER).append(":").append(PORT).append(";")
                .append("databaseName=").append(DATABASE_NAME).append(";")
                .append("user=").append(USERNAME).append(";")
                .append("password=").append(PASSWORD).append(";")
                ;
        if (USING_SSL) {
            connectStringBuilder.append("encrypt=true;trustServerCertificate=true;");
        }
        CONNECT_STRING = connectStringBuilder.toString();
	}
    
	
	public static String getConnectString() {
		return CONNECT_STRING;
	}
}
