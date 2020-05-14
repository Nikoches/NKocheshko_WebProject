create procedure list_early_deaths () return refcursor as
declare
    toesup refcursor;
begin
    open toesup for
        SELECT poets.name, deaths.age
        FROM poets, deaths
        WHERE poets.id = deaths.mort_id
            AND deaths.age < 60;
    return toesup;
end;