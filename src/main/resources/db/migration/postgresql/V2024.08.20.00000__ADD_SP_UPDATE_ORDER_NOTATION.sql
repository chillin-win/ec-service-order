CREATE OR REPLACE PROCEDURE sp_update_order_notation(
    ai_id varchar,
    avc_notation varchar DEFAULT 'Updated Notation From Store Procedure'
)
LANGUAGE plpgsql
AS $$

    BEGIN
        UPDATE orders
        SET notation = avc_notation
        WHERE id = ai_id;
    COMMIT;

END;$$
