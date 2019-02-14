package by.htp.ellib.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.htp.ellib.controller.command.impl.AdminAddBook;
import by.htp.ellib.controller.command.impl.AdminGiveBookToUser;
import by.htp.ellib.controller.command.impl.AdminLookAllBook;
import by.htp.ellib.controller.command.impl.AdminLookAllBookedBook;
import by.htp.ellib.controller.command.impl.AdminLookAllFreeBook;
import by.htp.ellib.controller.command.impl.AdminLookAllUsers;
import by.htp.ellib.controller.command.impl.AdminReceiveBookFromUser;
import by.htp.ellib.controller.command.impl.AuthorizationCommand;
import by.htp.ellib.controller.command.impl.ChangeLocale;
import by.htp.ellib.controller.command.impl.FindBookCommand;
import by.htp.ellib.controller.command.impl.GoToAdminPage;
import by.htp.ellib.controller.command.impl.GoToIndexPageCommand;
import by.htp.ellib.controller.command.impl.GoToRegistrationCommand;
import by.htp.ellib.controller.command.impl.RegistrationCommand;
import by.htp.ellib.controller.command.impl.SearchBooksByGenre;
import by.htp.ellib.controller.command.impl.SearchFreeBookByGenre;

public class CommandProvider {
	// Command - перевести в ЕНАМ по аналогии с предыдущим проектом
	private Map<String, Command> commands = new HashMap<>();

	
	public CommandProvider() {
		commands.put("authorization", new AuthorizationCommand());
		commands.put("goToRegistratioPage", new GoToRegistrationCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("find_book", new FindBookCommand());
		commands.put("go_to_index", new GoToIndexPageCommand());
		commands.put("change_locale", new ChangeLocale());
		commands.put("search_genre", new SearchBooksByGenre());
		commands.put("show_books", new AdminLookAllBook());
		commands.put("show_users", new AdminLookAllUsers());
		commands.put("go_admin_page", new GoToAdminPage());
		commands.put("addBook", new AdminAddBook());
		commands.put("search_free_book_genre", new SearchFreeBookByGenre());
		commands.put("show_free_books", new AdminLookAllFreeBook());
		commands.put("show_booked_books", new AdminLookAllBookedBook());
		commands.put("giveBook", new AdminGiveBookToUser());
		commands.put("receiveBook", new AdminReceiveBookFromUser());
	
		
	
	}
	
	public Command getCommand(String commandName) {
		return commands.get(commandName);
	}
}
