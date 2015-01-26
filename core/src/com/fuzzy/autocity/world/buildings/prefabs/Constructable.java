package com.fuzzy.autocity.world.buildings.prefabs;

/**
 * Created by Whiplash on 1/25/2015.
 */
public interface Constructable {

    public void Construct();
    public void deConstruct();
    public boolean isConstructed();
}
