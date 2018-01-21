package jp.tst.audittool.apiresource.model;

import java.util.Properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatabaseConfig {
    /** The hib properties. */
    private Properties hibProperties;
}
