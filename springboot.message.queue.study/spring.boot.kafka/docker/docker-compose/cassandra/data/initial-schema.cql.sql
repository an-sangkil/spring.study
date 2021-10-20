-- noinspection SqlNoDataSourceInspectionForFile

-- Drop the keyspace if it exists
DROP KEYSPACE IF EXISTS hc_tracking_keyspace;

-- Create our keyspace
CREATE KEYSPACE hc_tracking_keyspace WITH replication =  { 'class': 'SimpleStrategy', 'replication_factor': 1 };


create user hc_server with password 'hc_server1!' superuser;

-- Use the keyspace
USE hc_tracking_keyspace;

-- Table: session_events
CREATE COLUMNFAMILY user_sessions (
  session_id text,
  ts timestamp,
  path text,
  PRIMARY KEY(session_id, ts)
);

CREATE TABLE users (
    user_id int PRIMARY KEY,
    fname text,
    lname text
);

CREATE TABLE employee (
  id int PRIMARY KEY,
  name text,
  address text,
  email text,
  age int
);


