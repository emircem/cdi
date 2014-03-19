package com.vaadin.cdi.example.view;

import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.example.util.CounterService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.maddon.label.RichText;
import org.vaadin.maddon.layouts.MVerticalLayout;

@CDIView
public class RootView extends AbstractView {

    // UI scoped
    @Inject
    private CounterService counterService;
    private Button button;

    @Override
    protected Component buildContent() {
        VerticalLayout layout = new MVerticalLayout();

        layout.addComponent(new RichText().withMarkDown(
                RootView.class.getResourceAsStream("/welcome.md")));

        final Label countLabel = new Label("UI scoped counter = "
                + counterService.get());
        
        countLabel.setDescription("Me here is a description!?");
        
        layout.addComponent(countLabel);

        Button incrementButton = new Button("Increment UI scoped");
        layout.addComponent(incrementButton);
        incrementButton.addClickListener((ClickEvent event) -> {
            countLabel.setValue("UI scoped counter = "
                    + counterService.next());
        });
        
        layout.addComponent(button);

        return layout;
    }
    
    @PostConstruct void doStuff() {
        button = new Button("How about me");
        System.out.println("It fails with lambda in @PostConstruct");
        button.addClickListener((ClickEvent e) -> {
            System.out.println("It fails :-(");
        });
        
    }

}
