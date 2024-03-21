package components;

public class Admin {
        private String adminName;
        private String adminId;

        public Admin(String adminName, String adminId) {
                this.adminName = adminName;
                this.adminId = adminId;
        }

        public String getAdminName() {
                return adminName;
        }

        public void setAdminName(String adminName) {
                this.adminName = adminName;
        }

        public String getAdminId() {
                return adminId;
        }

        public void setAdminId(String adminId) {
                this.adminId = adminId;
        }

        @Override
        public String toString() {
                return "Admin{" +
                            "adminName='" + adminName + '\'' +
                            ", adminId='" + adminId + '\'' +
                            '}';
        }
}
