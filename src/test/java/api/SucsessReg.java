package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SucsessReg {

        private String sessionToken;

        public SucsessReg(String sessionToken) {
            this.sessionToken = sessionToken;
        }

        public String getSessionToken() {
            return sessionToken;
        }

        private Integer id;
        private String authType;
        private String email;

        public SucsessReg(Integer id, String authType, String email) {
            this.id = id;
            this.authType = authType;
            this.email = email;
        }

        public Integer getId() {
            return id;
        }

        public String getAuthType() {
            return authType;
        }

        public String getEmail() {
            return email;
        }
    }

