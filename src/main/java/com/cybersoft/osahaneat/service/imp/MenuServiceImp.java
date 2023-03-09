public interface MenuServiceImp {
    boolean insertFood(
            MultipartFile file,
            String name,
            String description,
            double price,
            String instruction,
            int cate_res_id
    );
}