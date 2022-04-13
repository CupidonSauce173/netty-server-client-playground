package CupidonSauce173.objects;

import java.util.Map;
import java.util.Objects;

public class Group {

    public String name;
    public String tag;
    public int priority;
    public Boolean invisible;
    public Map<String, Permission> permissionMap;

    private Group(Builder builder) {
        Objects.requireNonNull(builder.name);
        name = builder.name;
        tag = builder.tag;
        priority = builder.priority;
        invisible = builder.invisible;
        permissionMap = builder.permissionMap;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String tag;
        private int priority;
        private Boolean invisible;
        private Map<String, Permission> permissionMap;

        private Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder setPriority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder setInvisible(Boolean invisible) {
            this.invisible = invisible;
            return this;
        }

        public Builder setPermissions(Map<String, Permission> permissionMap) {
            this.permissionMap = permissionMap;
            return this;
        }

        public Group build() {
            return new Group(this);
        }
    }
}
