package dev.xoapp.portals.factory;

import java.util.HashMap;

import dev.xoapp.portals.session.Session;
import lombok.Getter;

public class SessionFactory
{
    @Getter static HashMap<String, Session> sessions = new HashMap<>();
}
