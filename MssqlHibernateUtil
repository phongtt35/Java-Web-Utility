import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 * Lớp tiện ích Hibernate cho SQL Server với cấu hình kết nối cơ sở dữ liệu có thể chỉnh sửa.
 * @author Phong
 */
public final class MssqlHibernateUtil {

    // =======> Phần Cấu Hình Cần Chỉnh Sửa: Thông tin kết nối cơ sở dữ liệu
    private static final String USERNAME = "sa";                // Tên người dùng cơ sở dữ liệu
    private static final String PASSWORD = "123456";            // Mật khẩu cơ sở dữ liệu
    private static final String SERVER = "localhost";           // Máy chủ cơ sở dữ liệu
    private static final String PORT = "1433";                  // Cổng kết nối cơ sở dữ liệu
    private static final String DATABASE_NAME = "master";       // Tên cơ sở dữ liệu (không có dấu ngoặc vuông)
    private static final boolean USING_SSL = false;             // Cờ sử dụng SSL (Mã hóa kết nối)
    // <======= Kết thúc phần Cấu Hình Cần Chỉnh Sửa

    private static SessionFactory sessionFactory;

    private MssqlHibernateUtil() {
        // Ngăn không cho khởi tạo đối tượng của lớp này
    }

    /**
     * Khởi tạo và trả về SessionFactory của Hibernate (chỉ khởi tạo một lần).
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (MssqlHibernateUtil.class) {
                if (sessionFactory == null) {
                    try {
                        Configuration configuration = new Configuration();

                        // Thiết lập các thuộc tính cho Hibernate
                        Properties settings = new Properties();
                        settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect"); // Cấu hình Dialect cho SQL Server
                        settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Driver JDBC cho SQL Server
                        settings.put(Environment.URL, getConnectString()); // Cấu hình URL kết nối
                        settings.put(Environment.SHOW_SQL, "true"); // Hiển thị câu lệnh SQL trên console
                        // Uncomment để tự động tạo/ cập nhật schema (nếu cần)
                        // settings.put(Environment.HBM2DDL_AUTO, "update");

                        configuration.setProperties(settings);

                        // Thêm các lớp Entity đã chú thích ở đây (nếu có)
                        // configuration.addAnnotatedClass(ViecCanLam.class);

                        // Khởi tạo ServiceRegistry và SessionFactory
                        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties())
                                .build();

                        sessionFactory = configuration.buildSessionFactory(registry);
                        System.out.println("✅ Hibernate SessionFactory đã được khởi tạo thành công!");
                    } catch (Exception e) {
                        System.err.println("❌ Lỗi khi khởi tạo Hibernate: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
        return sessionFactory;
    }

    /**
     * Mở một phiên làm việc (Session) mới của Hibernate.
     */
    public static Session openSession() {
        return getSessionFactory().openSession();
    }

    /**
     * Tạo chuỗi kết nối (Connection String) cho cơ sở dữ liệu SQL Server.
     */
    private static String getConnectString() {
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:sqlserver://")
          .append(SERVER).append(":").append(PORT).append(";")
          .append("databaseName=").append(DATABASE_NAME).append(";")
          .append("user=").append(USERNAME).append(";")
          .append("password=").append(PASSWORD).append(";");

        if (USING_SSL) {
            sb.append("encrypt=true;trustServerCertificate=true;"); // Cấu hình SSL nếu sử dụng
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        try (Session session = openSession()) {
            System.out.println("Hibernate đã kết nối thành công!");
        } catch (Exception e) {
            System.err.println("Lỗi khi mở phiên làm việc: " + e.getMessage());
        }
    }
}
