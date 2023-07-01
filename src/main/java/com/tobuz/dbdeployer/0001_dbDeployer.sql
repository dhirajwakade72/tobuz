DELETE FROM file_entity
WHERE business_listing_id IN (
    SELECT b.id
    FROM business_listing b
    JOIN Business_Listing_Out_Let blo ON b.business_listing_out_let_id = blo.id
    WHERE
    blo.total_business_sale_price IS NULL
);
