package com.example.admin.flickerapp.view.searchactivity;

import com.example.admin.flickerapp.data.component.NetComponent;
import com.example.admin.flickerapp.util.CustomScope;

import dagger.Component;

/**
 * Created by Olay G on 10/12/2017.
 */
@CustomScope
@Component( dependencies = {NetComponent.class}, modules = {SearchActivityModule.class})
public interface SearchActivityComponent {
    void inject(SearchActivity searchActivity);
}
