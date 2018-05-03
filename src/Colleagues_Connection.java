import java.util.ArrayList;

public class Colleagues_Connection extends Connection{

	public Colleagues_Connection(Profile person1, Profile person2) {
		super(person1, person2);
	}

	@Override
	public boolean check(ArrayList<Connection> c_list) {
		return false;
	}

}
