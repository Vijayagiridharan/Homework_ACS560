package com.acs560.rest_api.views;

import com.acs560.rest_api.security.SecurityService;
import com.acs560.rest_api.views.Category.CategoryView;
import com.acs560.rest_api.views.MenuItem.MenuItemView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.lumo.LumoUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The main layout for the application
 */
@UIScope
@Component
public class MainLayout extends AppLayout {
	
	@Autowired
	private SecurityService securityService;

    private static final long serialVersionUID = -5291741451913578403L;

    /**
     * Constructor
     */
    public MainLayout(SecurityService securityService) {
    	this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Food Menu");
        logo.addClassNames(
            LumoUtility.FontSize.LARGE,
            LumoUtility.Margin.MEDIUM);
        
        // Get the current authenticated user
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication != null ? authentication.getName() : "Guest";
        String un = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Logout" + un , e -> securityService.logout());

        var header = new HorizontalLayout(new DrawerToggle(), logo, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
            LumoUtility.Padding.Vertical.NONE,
            LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header); 
    }

    /**
     * Create the drawer
     */
    private void createDrawer() {
        RouterLink categoriesLink = new RouterLink("Categories", CategoryView.class);
        categoriesLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink menuItemsLink = new RouterLink("Menu Items", MenuItemView.class);

        addToDrawer(new VerticalLayout(categoriesLink, menuItemsLink));
    }
}
