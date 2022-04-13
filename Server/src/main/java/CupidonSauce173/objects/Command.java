package CupidonSauce173.objects;


public class Command {
    public String name;
    public String permission;

    public Command(String name, String permission) {
        this.name = name;
        this.permission = permission;
    }

    public void registerExecutable(Client sender, String[] args) {

    }
}
