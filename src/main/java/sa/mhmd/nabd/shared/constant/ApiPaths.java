package sa.mhmd.nabd.shared.constant;

public class ApiPaths {
    public static final String BASE_URL = "/api/v1/nabd";
    public static final class CMS {
        public static final String BASE = BASE_URL + "/cms";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String UPLOAD = "/{contentId}/media";
    }

    public static final class DISCOVERY {
        public static final String BASE = BASE_URL + "/discovery";
        public static final String SEARCH = "/search";
    }
}
