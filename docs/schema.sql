-- Table 1: The lightweight metadata table for GraphQL
drop table if exists widget;
CREATE TABLE widget (
                        id INT IDENTITY PRIMARY KEY,
                        name VARCHAR(255),
                        price DECIMAL(10,2)
);

-- Table 2: The heavy LOB table for the WebFlux streaming endpoint
drop table if exists widget_image;
CREATE TABLE widget_image (
                              id INT IDENTITY PRIMARY KEY,
                              widget_id INT NOT NULL,
                              image_data VARBINARY(MAX),
                              CONSTRAINT fk_widget_image FOREIGN KEY (widget_id) REFERENCES widget(id) ON DELETE CASCADE
);