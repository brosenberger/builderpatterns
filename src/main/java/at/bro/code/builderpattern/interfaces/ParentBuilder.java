package at.bro.code.builderpattern.interfaces;

public interface ParentBuilder<PARENT, OBJECT> {
    void setParent(PARENT parent);

    PARENT build();
}
