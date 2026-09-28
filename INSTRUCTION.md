1. Узнать id контейнера командой `docker ps`. Id - самая первая колонка. У нужно контейнера под столбцом NAMES будет `simpleecommerce-db`
2. Скопировать `.csv` файлы. Они должны быть UTF-16 LE
```
Для удобства прямо в каталоге контейнера я создал папку inp и засунул туда все файлы. То что указываем в ковычках - путь на хосте
```
```bash
docker cp "inp/categories.csv" <id контейнера>:/tmp/categories.csv
docker cp "inp/products.csv" <id контейнера>:/tmp/products.csv
```
3. Войти в mssql
```bash
sudo docker exec -it <id контейнера> /opt/mssql-tools/bin/sqlcmd -S sqlserver -U sa -P "YourStrong@Passw0rd"
```
4. Ввести
```sql
USE SimpleECommerceDb;
GO
```
5. Удалить все товары и сбросить счетчик Id. То же для категорий:
```sql
DELETE FROM Products;
DBCC CHECKIDENT ('Products', RESEED, 0);
DELETE FROM Categories;
DBCC CHECKIDENT ('Categories', RESEED, 0);
GO
```
6. Добавить автоматическое проставление текущей даты и времени для таблицы `Categories` и `Products`
```sql
ALTER TABLE Categories ADD CONSTRAINT DF_Categories_CreatedAt DEFAULT SYSDATETIME() FOR CreatedAt;
ALTER TABLE Products ADD CONSTRAINT DF_Products_CreatedAt DEFAULT SYSDATETIME() FOR CreatedAt;
GO
```
7. Финальные команды по порядку: создание временной таблицы для импорта категорий, вставка данных из csv, вставка в оригинальную таблицу категорий данных по определенным полям из временной, удаление временной таблицы, те же действия с продуктами
```sql
CREATE TABLE #Categories(
Name NVARCHAR(100),
IsDeleted Bit);
BULK INSERT #Categories
FROM '/tmp/categories.csv'
WITH (
FIELDTERMINATOR = '|',
ROWTERMINATOR = '\n',
FIRSTROW = 1,
DATAFILETYPE = 'widechar');
INSERT INTO Categories (Name, IsDeleted)
SELECT * FROM #Categories;
DROP TABLE #Categories
CREATE TABLE #Products(
Name NVARCHAR(200),
Description NVARCHAR(1000),
Price DECIMAL(18,2),
StockQuantity INT,
ImageUrl NVARCHAR(500),
CategoryId INT,
IsDeleted Bit);
BULK INSERT #Products
FROM '/tmp/products.csv'
WITH (
FIELDTERMINATOR = '|',
ROWTERMINATOR = '\n',
FIRSTROW = 1,
DATAFILETYPE = 'widechar');
INSERT INTO Products (Name, Description, Price, StockQuantity, ImageUrl, CategoryId, IsDeleted)
SELECT * FROM #Products;
DROP TABLE #Products;
GO
```
8. Удаляем автоматическое проставление текущей даты и времени для таблицы `Categories` и `Producs` после импорта, иначе падает docker контейнер с api
```sql
ALTER TABLE Categories
DROP CONSTRAINT DF_Categories_CreatedAt;
ALTER TABLE Products
DROP CONSTRAINT DF_Products_CreatedAt;
GO
```