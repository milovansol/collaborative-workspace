CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       user_name VARCHAR(255) NOT NULL UNIQUE,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       tenant_id INT,
                       oauth_provider VARCHAR(255),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       is_deleted boolean DEFAULT false,
                       FOREIGN KEY (tenant_id) REFERENCES tenants(tenant_id)
);

CREATE TABLE roles (
                       role_id INT PRIMARY KEY AUTO_INCREMENT,
                       role_name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE user_roles (
                            user_id INT,
                            role_id INT,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(user_id),
                            FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE tenants (
                         tenant_id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL UNIQUE,
                         owner_id INT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         is_deleted boolean DEFAULT false,
                         FOREIGN KEY (owner_id) REFERENCES users(user_id)
);

CREATE TABLE notes (
                       note_id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       content TEXT,
                       tags VARCHAR(255),
                       sentiment VARCHAR(50),
                       version_history JSON,
                       tenant_id INT,
                       author_id INT,
                       createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (tenant_id) REFERENCES tenants(tenant_id),
                       FOREIGN KEY (author_id) REFERENCES users(user_id)
);

CREATE TABLE audit_logs (
                           log_id INT AUTO_INCREMENT PRIMARY KEY,
                           action VARCHAR(255) NOT NULL,
                           note_id INT,
                           user_id INT,
                           timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           details TEXT,
                           FOREIGN KEY (note_id) REFERENCES notes(note_id),
                           FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE analytics (
                           tenant_id INT PRIMARY KEY,
                           active_users_count INT,
                           most_active_users JSON,
                           note_edit_counts INT,
                           sentiment_analysis JSON,
                           FOREIGN KEY (tenant_id) REFERENCES tenants(tenant_id)
);

CREATE TABLE shared_notes (
                             share_id INT AUTO_INCREMENT PRIMARY KEY,
                             note_id INT,
                             shared_by INT,
                             shared_with JSON,
                             expires_at TIMESTAMP,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (note_id) REFERENCES notes(note_id),
                             FOREIGN KEY (shared_by) REFERENCES users(user_id)
);