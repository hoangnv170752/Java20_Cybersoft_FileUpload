import com.cybersoft.osahaneat.service.imp.MenuServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class MenuService implements MenuServiceImp{
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    FileStorageServiceImp fileStorageServiceImp;
    @Override
    public boolean insertFood(
            MultipartFile file,
            String name,
            String description,
            double price,
            String instruction,
            int cate_res_id
    )
        boolean isInsertSuccess = false;
        boolean isSuccess = fileStorageServiceImp.saveFiles(file);
        if (isSuccess) {
            try () {
                Food food = new Food();
                food.setName(name);
                food.setDesc(description);
                food.setPrice(price);
                food.setInstruction(instruction);
                food.setImage(file.getOriginalFilename());
                CategoryRestaurant categoryRestaurant = new CategoryRestaurant();
                categoryRestaurant.setId(categoryRestaurant);
                food.setCategoryRestaurant(categoryRestaurant);
                foodRepository.save(food);
                isInsertSuccess = true;
            } catch (Exception e) {
                System.out.println("Error insert food");
            }
        }
        return isInsertSuccess;
}