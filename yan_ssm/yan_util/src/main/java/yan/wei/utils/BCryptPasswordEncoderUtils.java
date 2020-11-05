package yan.wei.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword (String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String s = BCryptPasswordEncoderUtils.encodePassword("cctv2");
        //$2a$10$oxSMjmb1ZitMROtWmBDFpOCO/MMxmEJhsL4dbg9..2nFbBpBchnOS
        //$2a$10$q569KDHg073PUtLaR4m8VeNT9n8xGE9PZnLFOwvVcOGLyS/e4z/r2
        System.out.println(s);
        //cctv3: 密码123123
    }
}
