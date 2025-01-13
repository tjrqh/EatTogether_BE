INSERT INTO rs_cuisine_categories (type) VALUES ('KOREAN');
INSERT INTO rs_cuisine_categories (type) VALUES ('JAPANESE');
INSERT INTO rs_cuisine_categories (type) VALUES ('CHINESE');
INSERT INTO rs_cuisine_categories (type) VALUES ('WESTERN');
INSERT INTO rs_cuisine_categories (type) VALUES ('FUSION');
INSERT INTO rs_cuisine_categories (type) VALUES ('CAFE');
INSERT INTO rs_cuisine_categories (type) VALUES ('OTHER');
INSERT INTO rs_cuisine_categories (type) VALUES ('ITALIAN');
INSERT INTO rs_cuisine_categories (type) VALUES ('FRENCH');
INSERT INTO rs_cuisine_categories (type) VALUES ('INDIAN');
INSERT INTO rs_cuisine_categories (type) VALUES ('MEXICAN');
INSERT INTO rs_cuisine_categories (type) VALUES ('AMERICAN');
INSERT INTO rs_cuisine_categories (type) VALUES ('VIETNAMESE');
INSERT INTO rs_cuisine_categories (type) VALUES ('THAI');

INSERT INTO member (email, password, name, nickname, phone) VALUES
    ('owner@test.com', '$2a$10$yWtxG5QgVwwGjHeVqXh6Nu4g.ED2w3KoNZz8TjUXdg5h8.cDyhJBi', '사장님', '사장님', '010-1234-5678');

-- 3. Documents for Korean Restaurants (1-10)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/korean/1', 'korean_license_1.pdf', '123-45-67801'),
                                                                                    ('/documents/korean/2', 'korean_license_2.pdf', '123-45-67802'),
                                                                                    ('/documents/korean/3', 'korean_license_3.pdf', '123-45-67803'),
                                                                                    ('/documents/korean/4', 'korean_license_4.pdf', '123-45-67804'),
                                                                                    ('/documents/korean/5', 'korean_license_5.pdf', '123-45-67805'),
                                                                                    ('/documents/korean/6', 'korean_license_6.pdf', '123-45-67806'),
                                                                                    ('/documents/korean/7', 'korean_license_7.pdf', '123-45-67807'),
                                                                                    ('/documents/korean/8', 'korean_license_8.pdf', '123-45-67808'),
                                                                                    ('/documents/korean/9', 'korean_license_9.pdf', '123-45-67809'),
                                                                                    ('/documents/korean/10', 'korean_license_10.pdf', '123-45-67810');

-- 4. Korean Restaurants (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('강남면옥', '02-1111-1111', '11:00-22:00', 'OPEN', 4.5, false, 0,
       '정통 평양냉면 전문점', '서울시 강남구 강남대로', '123번길 45', '06000',
       1, 1, 1),

      ('서울정식당', '02-1111-1112', '11:30-21:30', 'OPEN', 4.7, true, 10000,
       '한정식 전문점', '서울시 서초구 서초대로', '78번길 90', '06001',
       1, 1, 2),

      ('춘천닭갈비', '02-1111-1113', '12:00-22:00', 'OPEN', 4.3, false, 0,
       '춘천식 닭갈비', '서울시 송파구 송파대로', '34번길 56', '05000',
       1, 1, 3),

      ('왕십리곱창', '02-1111-1114', '16:00-02:00', 'OPEN', 4.6, false, 0,
       '특제양념 곱창구이', '서울시 성동구 왕십리로', '89번길 12', '04000',
       1, 1, 4),

      ('을지로파전', '02-1111-1115', '11:00-23:00', 'OPEN', 4.4, false, 0,
       '해물파전 전문점', '서울시 중구 을지로', '45번길 67', '04001',
       1, 1, 5),

      ('종로육회', '02-1111-1116', '11:30-22:30', 'OPEN', 4.8, true, 15000,
       '최상급 한우육회', '서울시 종로구 종로', '12번길 34', '03000',
       1, 1, 6),

      ('신촌설렁탕', '02-1111-1117', '06:00-22:00', 'OPEN', 4.2, false, 0,
       '100년 전통 설렁탕', '서울시 서대문구 신촌로', '56번길 78', '03001',
       1, 1, 7),

      ('마포갈매기', '02-1111-1118', '12:00-23:00', 'OPEN', 4.5, false, 0,
       '갈매기살 전문점', '서울시 마포구 마포대로', '90번길 12', '04000',
       1, 1, 8),

      ('압구정김밥', '02-1111-1119', '08:00-21:00', 'OPEN', 4.1, false, 0,
       '고급 김밥 전문점', '서울시 강남구 압구정로', '34번길 56', '06000',
       1, 1, 9),

      ('역삼감자탕', '02-1111-1120', '10:00-22:00', 'OPEN', 4.4, false, 0,
       '뼈다귀 감자탕', '서울시 강남구 역삼로', '78번길 90', '06002',
       1, 1, 10);

-- 5. Documents for Japanese Restaurants (11-20)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/japanese/1', 'japanese_license_1.pdf', '123-45-67811'),
                                                                                    ('/documents/japanese/2', 'japanese_license_2.pdf', '123-45-67812'),
                                                                                    ('/documents/japanese/3', 'japanese_license_3.pdf', '123-45-67813'),
                                                                                    ('/documents/japanese/4', 'japanese_license_4.pdf', '123-45-67814'),
                                                                                    ('/documents/japanese/5', 'japanese_license_5.pdf', '123-45-67815'),
                                                                                    ('/documents/japanese/6', 'japanese_license_6.pdf', '123-45-67816'),
                                                                                    ('/documents/japanese/7', 'japanese_license_7.pdf', '123-45-67817'),
                                                                                    ('/documents/japanese/8', 'japanese_license_8.pdf', '123-45-67818'),
                                                                                    ('/documents/japanese/9', 'japanese_license_9.pdf', '123-45-67819'),
                                                                                    ('/documents/japanese/10', 'japanese_license_10.pdf', '123-45-67820');

-- 6. Japanese Restaurants (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('스시히로', '02-2222-1111', '11:30-22:00', 'OPEN', 4.8, true, 50000,
       '오마카세 전문점', '서울시 강남구 신사동', '123번길 45', '06000',
       1, 2, 11),

      ('우동야', '02-2222-1112', '11:00-21:30', 'OPEN', 4.3, false, 0,
       '사누끼 우동', '서울시 마포구 홍대로', '67번길 89', '04000',
       1, 2, 12),

      ('라멘조', '02-2222-1113', '11:30-21:00', 'OPEN', 4.5, false, 0,
       '돈코츠 라멘', '서울시 서초구 서초중앙로', '12번길 34', '06001',
       1, 2, 13),

      ('돈까스박사', '02-2222-1114', '11:00-22:00', 'OPEN', 4.4, false, 0,
       '특제소스 돈까스', '서울시 강남구 테헤란로', '56번길 78', '06002',
       1, 2, 14),

      ('이자카야킨', '02-2222-1115', '17:00-02:00', 'OPEN', 4.6, false, 0,
       '정통 이자카야', '서울시 강남구 역삼로', '90번길 12', '06003',
       1, 2, 15),

      ('스시도', '02-2222-1116', '11:30-22:00', 'OPEN', 4.7, true, 30000,
       '모던 스시', '서울시 서초구 반포대로', '34번길 56', '06004',
       1, 2, 16),

      ('규카츠야', '02-2222-1117', '11:30-21:30', 'OPEN', 4.2, false, 0,
       '차돌규카츠', '서울시 종로구 종로', '78번길 90', '03000',
       1, 2, 17),

      ('소바헤븐', '02-2222-1118', '11:00-21:00', 'OPEN', 4.5, false, 0,
       '자가제면 소바', '서울시 중구 명동길', '12번길 34', '04001',
       1, 2, 18),

      ('오코노미야키', '02-2222-1119', '11:30-22:00', 'OPEN', 4.3, false, 0,
       '히로시마식 오코노미야키', '서울시 용산구 이태원로', '56번길 78', '04002',
       1, 2, 19),

      ('덮밥천국', '02-2222-1120', '11:00-21:30', 'OPEN', 4.4, false, 0,
       '특제소스 덮밥', '서울시 강남구 삼성로', '90번길 12', '06005',
       1, 2, 20);

-- 7. Documents for Chinese Restaurants (21-30)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/chinese/1', 'chinese_license_1.pdf', '123-45-67821'),
                                                                                    ('/documents/chinese/2', 'chinese_license_2.pdf', '123-45-67822'),
                                                                                    ('/documents/chinese/3', 'chinese_license_3.pdf', '123-45-67823'),
                                                                                    ('/documents/chinese/4', 'chinese_license_4.pdf', '123-45-67824'),
                                                                                    ('/documents/chinese/5', 'chinese_license_5.pdf', '123-45-67825'),
                                                                                    ('/documents/chinese/6', 'chinese_license_6.pdf', '123-45-67826'),
                                                                                    ('/documents/chinese/7', 'chinese_license_7.pdf', '123-45-67827'),
                                                                                    ('/documents/chinese/8', 'chinese_license_8.pdf', '123-45-67828'),
                                                                                    ('/documents/chinese/9', 'chinese_license_9.pdf', '123-45-67829'),
                                                                                    ('/documents/chinese/10', 'chinese_license_10.pdf', '123-45-67830');

-- 8. Chinese Restaurants (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('홍콩반점', '02-3333-1111', '11:00-22:00', 'OPEN', 4.3, false, 0,
       '정통 중화요리', '서울시 중구 명동길', '123번길 45', '04001',
       1, 3, 21),

      ('양자강', '02-3333-1112', '11:30-22:00', 'OPEN', 4.5, false, 0,
       '산둥식 요리', '서울시 강남구 강남대로', '67번길 89', '06000',
       1, 3, 22),

      ('마라왕朝', '02-3333-1113', '11:00-23:00', 'OPEN', 4.6, false, 0,
       '마라탕 전문점', '서울시 서초구 서초대로', '12번길 34', '06001',
       1, 3, 23),

      ('북경덕', '02-3333-1114', '11:30-22:00', 'OPEN', 4.7, true, 20000,
       '베이징덕 전문점', '서울시 종로구 종로', '56번길 78', '03000',
       1, 3, 24),

      ('사천성', '02-3333-1115', '11:00-22:30', 'OPEN', 4.4, false, 0,
       '매운 사천요리', '서울시 마포구 마포대로', '90번길 12', '04000',
       1, 3, 25),

      ('상해루', '02-3333-1116', '11:30-21:30', 'OPEN', 4.2, false, 0,
       '상해식 요리', '서울시 송파구 송파대로', '34번길 56', '05000',
       1, 3, 26),

      ('딤섬하우스', '02-3333-1117', '11:00-22:00', 'OPEN', 4.5, false, 0,
       '광둥식 딤섬', '서울시 강남구 테헤란로', '78번길 90', '06002',
       1, 3, 27),

      ('취향관', '02-3333-1118', '11:30-22:30', 'OPEN', 4.6, false, 0,
       '산시식 요리', '서울시 서초구 반포대로', '12번길 34', '06004',
       1, 3, 28),

      ('만두천국', '02-3333-1119', '11:00-21:00', 'OPEN', 4.3, false, 0,
       '수제 만두전문', '서울시 중구 을지로', '56번길 78', '04001',
       1, 3, 29),

      ('황제반점', '02-3333-1120', '11:30-22:00', 'OPEN', 4.4, false, 0,
       '고급 중화요리', '서울시 강남구 역삼로', '90번길 12', '06003',
       1, 3, 30);

-- 9. Documents for Western Restaurants (31-40)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/western/1', 'western_license_1.pdf', '123-45-67831'),
                                                                                    ('/documents/western/2', 'western_license_2.pdf', '123-45-67832'),
                                                                                    ('/documents/western/3', 'western_license_3.pdf', '123-45-67833'),
                                                                                    ('/documents/western/4', 'western_license_4.pdf', '123-45-67834'),
                                                                                    ('/documents/western/5', 'western_license_5.pdf', '123-45-67835'),
                                                                                    ('/documents/western/6', 'western_license_6.pdf', '123-45-67836'),
                                                                                    ('/documents/western/7', 'western_license_7.pdf', '123-45-67837'),
                                                                                    ('/documents/western/8', 'western_license_8.pdf', '123-45-67838'),
                                                                                    ('/documents/western/9', 'western_license_9.pdf', '123-45-67839'),
                                                                                    ('/documents/western/10', 'western_license_10.pdf', '123-45-67840');

-- 10. Western Restaurants (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('더스테이크', '02-4444-1111', '11:30-22:00', 'OPEN', 4.7, true, 30000,
       '프리미엄 스테이크', '서울시 강남구 선릉로', '123번길 45', '06000',
       1, 4, 31),

      ('파스타공방', '02-4444-1112', '11:00-22:00', 'OPEN', 4.4, false, 0,
       '수제 파스타', '서울시 서초구 서초대로', '67번길 89', '06001',
       1, 4, 32),

      ('비스트로서울', '02-4444-1113', '11:30-22:00', 'OPEN', 4.6, true, 20000,
       '모던 프렌치', '서울시 용산구 이태원로', '12번길 34', '04002',
       1, 4, 33),

      ('그릴하우스', '02-4444-1114', '11:00-22:00', 'OPEN', 4.5, false, 0,
       'BBQ 전문점', '서울시 마포구 와우산로', '56번길 78', '04000',
       1, 4, 34),

      ('살루테', '02-4444-1115', '11:30-21:30', 'OPEN', 4.3, false, 0,
       '이탈리안 다이닝', '서울시 종로구 삼청로', '90번길 12', '03000',
       1, 4, 35),

      ('브라세리', '02-4444-1116', '11:30-22:00', 'OPEN', 4.8, true, 25000,
       '프렌치 비스트로', '서울시 강남구 압구정로', '34번길 56', '06000',
       1, 4, 36),

      ('라트라토리아', '02-4444-1117', '11:00-22:00', 'OPEN', 4.5, false, 0,
       '캐주얼 이탈리안', '서울시 서초구 반포대로', '78번길 90', '06001',
       1, 4, 37),

      ('더플레이트', '02-4444-1118', '11:30-22:00', 'OPEN', 4.6, true, 15000,
       '컨템포러리 유러피안', '서울시 강남구 도산대로', '12번길 34', '06000',
       1, 4, 38),

      ('르빠숑', '02-4444-1119', '11:00-21:30', 'OPEN', 4.4, false, 0,
       '프렌치 비스트로', '서울시 용산구 한남대로', '56번길 78', '04002',
       1, 4, 39),

      ('빈센조', '02-4444-1120', '11:30-22:00', 'OPEN', 4.7, true, 20000,
       '오리지널 이탈리안', '서울시 강남구 신사동', '90번길 12', '06000',
       1, 4, 40);

-- 11. Documents for Fusion Restaurants (41-50)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/fusion/1', 'fusion_license_1.pdf', '123-45-67841'),
                                                                                    ('/documents/fusion/2', 'fusion_license_2.pdf', '123-45-67842'),
                                                                                    ('/documents/fusion/3', 'fusion_license_3.pdf', '123-45-67843'),
                                                                                    ('/documents/fusion/4', 'fusion_license_4.pdf', '123-45-67844'),
                                                                                    ('/documents/fusion/5', 'fusion_license_5.pdf', '123-45-67845'),
                                                                                    ('/documents/fusion/6', 'fusion_license_6.pdf', '123-45-67846'),
                                                                                    ('/documents/fusion/7', 'fusion_license_7.pdf', '123-45-67847'),
                                                                                    ('/documents/fusion/8', 'fusion_license_8.pdf', '123-45-67848'),
                                                                                    ('/documents/fusion/9', 'fusion_license_9.pdf', '123-45-67849'),
                                                                                    ('/documents/fusion/10', 'fusion_license_10.pdf', '123-45-67850');

-- 12. Fusion Restaurants (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('모던한식', '02-5555-1111', '11:30-22:00', 'OPEN', 4.7, true, 25000,
       '현대적 한식당', '서울시 강남구 선릉로', '123번길 45', '06000',
       1, 5, 41),

      ('퓨전앤코', '02-5555-1112', '11:00-22:00', 'OPEN', 4.5, false, 0,
       '아시안퓨전', '서울시 서초구 서초대로', '67번길 89', '06001',
       1, 5, 42),

      ('크리에이티브', '02-5555-1113', '11:30-22:00', 'OPEN', 4.8, true, 30000,
       '창의적 퓨전요리', '서울시 용산구 이태원로', '12번길 34', '04002',
       1, 5, 43),

      ('뉴코리안', '02-5555-1114', '11:00-22:00', 'OPEN', 4.6, false, 0,
       '신한식당', '서울시 마포구 와우산로', '56번길 78', '04000',
       1, 5, 44),

      ('하이브리드', '02-5555-1115', '11:30-21:30', 'OPEN', 4.4, false, 0,
       '동서양 퓨전', '서울시 종로구 삼청로', '90번길 12', '03000',
       1, 5, 45),

      ('모던테이블', '02-5555-1116', '11:30-22:00', 'OPEN', 4.9, true, 35000,
       '컨템포러리 퓨전', '서울시 강남구 압구정로', '34번길 56', '06000',
       1, 5, 46),

      ('퓨전랩', '02-5555-1117', '11:00-22:00', 'OPEN', 4.5, false, 0,
       '실험적 퓨전', '서울시 서초구 반포대로', '78번길 90', '06001',
       1, 5, 47),

      ('크로스오버', '02-5555-1118', '11:30-22:00', 'OPEN', 4.7, true, 20000,
       '다국적 퓨전', '서울시 강남구 도산대로', '12번길 34', '06000',
       1, 5, 48),

      ('퓨전스페이스', '02-5555-1119', '11:00-21:30', 'OPEN', 4.3, false, 0,
       '모던 퓨전', '서울시 용산구 한남대로', '56번길 78', '04002',
       1, 5, 49),

      ('뉴웨이브', '02-5555-1120', '11:30-22:00', 'OPEN', 4.6, true, 25000,
       '트렌디 퓨전', '서울시 강남구 신사동', '90번길 12', '06000',
       1, 5, 50);

-- 13. Documents for Cafe (51-60)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/cafe/1', 'cafe_license_1.pdf', '123-45-67851'),
                                                                                    ('/documents/cafe/2', 'cafe_license_2.pdf', '123-45-67852'),
                                                                                    ('/documents/cafe/3', 'cafe_license_3.pdf', '123-45-67853'),
                                                                                    ('/documents/cafe/4', 'cafe_license_4.pdf', '123-45-67854'),
                                                                                    ('/documents/cafe/5', 'cafe_license_5.pdf', '123-45-67855'),
                                                                                    ('/documents/cafe/6', 'cafe_license_6.pdf', '123-45-67856'),
                                                                                    ('/documents/cafe/7', 'cafe_license_7.pdf', '123-45-67857'),
                                                                                    ('/documents/cafe/8', 'cafe_license_8.pdf', '123-45-67858'),
                                                                                    ('/documents/cafe/9', 'cafe_license_9.pdf', '123-45-67859'),
                                                                                    ('/documents/cafe/10', 'cafe_license_10.pdf', '123-45-67860');

-- 14. Cafes (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('블루보틀', '02-6666-1111', '08:00-21:00', 'OPEN', 4.5, false, 0,
       '스페셜티 커피', '서울시 강남구 삼성로', '123번길 45', '06000',
       1, 6, 51),

      ('디저트랩', '02-6666-1112', '10:00-22:00', 'OPEN', 4.7, false, 0,
       '디저트 전문카페', '서울시 서초구 서초대로', '67번길 89', '06001',
       1, 6, 52),

      ('브런치카페', '02-6666-1113', '09:00-20:00', 'OPEN', 4.4, false, 0,
       '브런치&커피', '서울시 마포구 와우산로', '12번길 34', '04000',
       1, 6, 53),

      ('커피로드', '02-6666-1114', '08:30-21:30', 'OPEN', 4.6, false, 0,
       '로스터리카페', '서울시 종로구 삼청로', '56번길 78', '03000',
       1, 6, 54),

      ('스위트스팟', '02-6666-1115', '11:00-21:00', 'OPEN', 4.8, false, 0,
       '디저트카페', '서울시 강남구 도산대로', '90번길 12', '06000',
       1, 6, 55),

      ('카페테리아', '02-6666-1116', '09:00-22:00', 'OPEN', 4.3, false, 0,
       '올데이카페', '서울시 서초구 반포대로', '34번길 56', '06001',
       1, 6, 56),

      ('베이커리카페', '02-6666-1117', '08:00-20:00', 'OPEN', 4.5, false, 0,
       '수제 베이커리', '서울시 용산구 이태원로', '78번길 90', '04002',
       1, 6, 57),

      ('커피앤디저트', '02-6666-1118', '10:00-21:00', 'OPEN', 4.7, false, 0,
       '디저트전문점', '서울시 마포구 홍대로', '12번길 34', '04000',
       1, 6, 58),

      ('티하우스', '02-6666-1119', '11:00-20:00', 'OPEN', 4.4, false, 0,
       '전통차카페', '서울시 종로구 인사동길', '56번길 78', '03000',
       1, 6, 59),

      ('브런치앤코', '02-6666-1120', '09:00-21:00', 'OPEN', 4.6, false, 0,
       '브런치카페', '서울시 강남구 압구정로', '90번길 12', '06000',
       1, 6, 60);

-- 15. Documents for Others (61-70)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/other/1', 'other_license_1.pdf', '123-45-67861'),
                                                                                    ('/documents/other/2', 'other_license_2.pdf', '123-45-67862'),
                                                                                    ('/documents/other/3', 'other_license_3.pdf', '123-45-67863'),
                                                                                    ('/documents/other/4', 'other_license_4.pdf', '123-45-67864'),
                                                                                    ('/documents/other/5', 'other_license_5.pdf', '123-45-67865'),
                                                                                    ('/documents/other/6', 'other_license_6.pdf', '123-45-67866'),
                                                                                    ('/documents/other/7', 'other_license_7.pdf', '123-45-67867'),
                                                                                    ('/documents/other/8', 'other_license_8.pdf', '123-45-67868'),
                                                                                    ('/documents/other/9', 'other_license_9.pdf', '123-45-67869'),
                                                                                    ('/documents/other/10', 'other_license_10.pdf', '123-45-67870');

-- 16. Other Restaurants (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('월드키친', '02-7777-1111', '11:30-22:00', 'OPEN', 4.5, false, 0,
       '세계음식점', '서울시 강남구 선릉로', '123번길 45', '06000',
       1, 7, 61),

      ('푸드랩', '02-7777-1112', '11:00-22:00', 'OPEN', 4.6, false, 0,
       '실험적요리', '서울시 서초구 서초대로', '67번길 89', '06001',
       1, 7, 62),

      ('시즈널', '02-7777-1113', '11:30-21:30', 'OPEN', 4.7, true, 20000,
       '계절음식', '서울시 마포구 와우산로', '12번길 34', '04000',
       1, 7, 63),

      ('로컬푸드', '02-7777-1114', '11:00-22:00', 'OPEN', 4.4, false, 0,
       '지역식재료', '서울시 종로구 삼청로', '56번길 78', '03000',
       1, 7, 64),

      ('헬시키친', '02-7777-1115', '09:00-21:00', 'OPEN', 4.8, false, 0,
       '건강식전문', '서울시 강남구 도산대로', '90번길 12', '06000',
       1, 7, 65),

      ('글로벌다이닝', '02-7777-1116', '11:30-22:00', 'OPEN', 4.5, true, 25000,
       '세계요리', '서울시 서초구 반포대로', '34번길 56', '06001',
       1, 7, 66),

      ('비건키친', '02-7777-1117', '11:00-21:00', 'OPEN', 4.6, false, 0,
       '비건전문점', '서울시 용산구 이태원로', '78번길 90', '04002',
       1, 7, 67),

      ('시그니처', '02-7777-1118', '11:30-22:00', 'OPEN', 4.7, true, 30000,
       '창작요리', '서울시 마포구 홍대로', '12번길 34', '04000',
       1, 7, 68),

      ('모던키친', '02-7777-1119', '11:00-21:30', 'OPEN', 4.4, false, 0,
       '현대식요리', '서울시 종로구 인사동길', '56번길 78', '03000',
       1, 7, 69),

      ('네이처키친', '02-7777-1120', '11:30-22:00', 'OPEN', 4.6, false, 0,
       '자연주의요리', '서울시 강남구 압구정로', '90번길 12', '06000',
       1, 7, 70);

-- 17. Documents for Italian Restaurants (71-80)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/italian/1', 'italian_license_1.pdf', '123-45-67871'),
                                                                                    ('/documents/italian/2', 'italian_license_2.pdf', '123-45-67872'),
                                                                                    ('/documents/italian/3', 'italian_license_3.pdf', '123-45-67873'),
                                                                                    ('/documents/italian/4', 'italian_license_4.pdf', '123-45-67874'),
                                                                                    ('/documents/italian/5', 'italian_license_5.pdf', '123-45-67875'),
                                                                                    ('/documents/italian/6', 'italian_license_6.pdf', '123-45-67876'),
                                                                                    ('/documents/italian/7', 'italian_license_7.pdf', '123-45-67877'),
                                                                                    ('/documents/italian/8', 'italian_license_8.pdf', '123-45-67878'),
                                                                                    ('/documents/italian/9', 'italian_license_9.pdf', '123-45-67879'),
                                                                                    ('/documents/italian/10', 'italian_license_10.pdf', '123-45-67880');

-- 18. Italian Restaurants (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('피자리아', '02-8888-1111', '11:00-22:00', 'OPEN', 4.7, false, 0,
       '나폴리피자', '서울시 강남구 선릉로', '123번길 45', '06000',
       1, 8, 71),

      ('트라토리아', '02-8888-1112', '11:30-22:00', 'OPEN', 4.8, true, 30000,
       '정통이탈리안', '서울시 서초구 서초대로', '67번길 89', '06001',
       1, 8, 72),

      ('파스타바', '02-8888-1113', '11:00-21:30', 'OPEN', 4.5, false, 0,
       '수제파스타', '서울시 마포구 와우산로', '12번길 34', '04000',
       1, 8, 73),

      ('리스토란테', '02-8888-1114', '11:30-22:00', 'OPEN', 4.9, true, 40000,
       '고급이탈리안', '서울시 종로구 삼청로', '56번길 78', '03000',
       1, 8, 74),

      ('오스테리아', '02-8888-1115', '11:00-22:00', 'OPEN', 4.6, false, 0,
       '캐주얼이탈리안', '서울시 강남구 도산대로', '90번길 12', '06000',
       1, 8, 75),

      ('피자고메', '02-8888-1116', '11:00-21:30', 'OPEN', 4.4, false, 0,
       '고메피자', '서울시 서초구 반포대로', '34번길 56', '06001',
       1, 8, 76),

      ('안티파스토', '02-8888-1117', '11:30-22:00', 'OPEN', 4.7, true, 25000,
       '이탈리안바', '서울시 용산구 이태원로', '78번길 90', '04002',
       1, 8, 77),

      ('파스타팩토리', '02-8888-1118', '11:00-22:00', 'OPEN', 4.5, false, 0,
       '파스타전문', '서울시 마포구 홍대로', '12번길 34', '04000',
       1, 8, 78),

      ('살루미', '02-8888-1119', '11:30-21:30', 'OPEN', 4.6, false, 0,
       '와인&파스타', '서울시 종로구 인사동길', '56번길 78', '03000',
       1, 8, 79),

      ('피자에리아', '02-8888-1120', '11:00-22:00', 'OPEN', 4.8, true, 20000,
       '로마피자', '서울시 강남구 압구정로', '90번길 12', '06000',
       1, 8, 80);

-- 19. Documents for French Restaurants (81-90)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/french/1', 'french_license_1.pdf', '123-45-67881'),
                                                                                    ('/documents/french/2', 'french_license_2.pdf', '123-45-67882'),
                                                                                    ('/documents/french/3', 'french_license_3.pdf', '123-45-67883'),
                                                                                    ('/documents/french/4', 'french_license_4.pdf', '123-45-67884'),
                                                                                    ('/documents/french/5', 'french_license_5.pdf', '123-45-67885'),
                                                                                    ('/documents/french/6', 'french_license_6.pdf', '123-45-67886'),
                                                                                    ('/documents/french/7', 'french_license_7.pdf', '123-45-67887'),
                                                                                    ('/documents/french/8', 'french_license_8.pdf', '123-45-67888'),
                                                                                    ('/documents/french/9', 'french_license_9.pdf', '123-45-67889'),
                                                                                    ('/documents/french/10', 'french_license_10.pdf', '123-45-67890');

-- 20. French Restaurants (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('르꼬숑', '02-9999-1111', '11:30-22:00', 'OPEN', 4.9, true, 50000,
       '정통 프렌치', '서울시 강남구 선릉로', '123번길 45', '06000',
       1, 9, 81),

      ('비스트로파리', '02-9999-1112', '11:00-22:00', 'OPEN', 4.7, true, 30000,
       '캐주얼 프렌치', '서울시 서초구 서초대로', '67번길 89', '06001',
       1, 9, 82),

      ('라뺑', '02-9999-1113', '08:00-21:00', 'OPEN', 4.5, false, 0,
       '프렌치 베이커리', '서울시 마포구 와우산로', '12번길 34', '04000',
       1, 9, 83),

      ('르부르고뉴', '02-9999-1114', '11:30-22:00', 'OPEN', 4.8, true, 40000,
       '와인&다이닝', '서울시 종로구 삼청로', '56번길 78', '03000',
       1, 9, 84),

      ('메종드', '02-9999-1115', '11:00-21:30', 'OPEN', 4.6, false, 0,
       '프렌치 비스트로', '서울시 강남구 도산대로', '90번길 12', '06000',
       1, 9, 85),

      ('라캔틴', '02-9999-1116', '11:30-22:00', 'OPEN', 4.7, true, 35000,
       '모던 프렌치', '서울시 서초구 반포대로', '34번길 56', '06001',
       1, 9, 86),

      ('르샤토', '02-9999-1117', '11:00-22:00', 'OPEN', 4.8, true, 45000,
       '럭셔리 프렌치', '서울시 용산구 이태원로', '78번길 90', '04002',
       1, 9, 87),

      ('라테라스', '02-9999-1118', '11:30-21:30', 'OPEN', 4.5, false, 0,
       '테라스 레스토랑', '서울시 마포구 홍대로', '12번길 34', '04000',
       1, 9, 88),

      ('르자르뎅', '02-9999-1119', '11:00-22:00', 'OPEN', 4.6, true, 25000,
       '가든 레스토랑', '서울시 종로구 인사동길', '56번길 78', '03000',
       1, 9, 89),

      ('비스트로노미', '02-9999-1120', '11:30-22:00', 'OPEN', 4.7, true, 30000,
       '비스트로노미', '서울시 강남구 압구정로', '90번길 12', '06000',
       1, 9, 90);
-- 21. Documents for Indian Restaurants (91-100)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/indian/1', 'indian_license_1.pdf', '123-45-67891'),
                                                                                    ('/documents/indian/2', 'indian_license_2.pdf', '123-45-67892'),
                                                                                    ('/documents/indian/3', 'indian_license_3.pdf', '123-45-67893'),
                                                                                    ('/documents/indian/4', 'indian_license_4.pdf', '123-45-67894'),
                                                                                    ('/documents/indian/5', 'indian_license_5.pdf', '123-45-67895'),
                                                                                    ('/documents/indian/6', 'indian_license_6.pdf', '123-45-67896'),
                                                                                    ('/documents/indian/7', 'indian_license_7.pdf', '123-45-67897'),
                                                                                    ('/documents/indian/8', 'indian_license_8.pdf', '123-45-67898'),
                                                                                    ('/documents/indian/9', 'indian_license_9.pdf', '123-45-67899'),
                                                                                    ('/documents/indian/10', 'indian_license_10.pdf', '123-45-67900');

-- 22. Indian Restaurants (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('타지', '02-1010-1111', '11:00-22:00', 'OPEN', 4.6, false, 0,
       '정통 인도요리', '서울시 강남구 선릉로', '123번길 45', '06000',
       1, 10, 91),

      ('커리하우스', '02-1010-1112', '11:30-22:00', 'OPEN', 4.5, false, 0,
       '커리전문점', '서울시 서초구 서초대로', '67번길 89', '06001',
       1, 10, 92),

      ('난드완', '02-1010-1113', '11:00-21:30', 'OPEN', 4.7, true, 20000,
       '탄두리전문', '서울시 마포구 와우산로', '12번길 34', '04000',
       1, 10, 93),

      ('뭄바이', '02-1010-1114', '11:30-22:00', 'OPEN', 4.4, false, 0,
       '뭄바이음식', '서울시 종로구 삼청로', '56번길 78', '03000',
       1, 10, 94),

      ('델리인디아', '02-1010-1115', '11:00-22:00', 'OPEN', 4.8, true, 25000,
       '델리스타일', '서울시 강남구 도산대로', '90번길 12', '06000',
       1, 10, 95),

      ('마살라', '02-1010-1116', '11:30-21:30', 'OPEN', 4.5, false, 0,
       '향신료전문', '서울시 서초구 반포대로', '34번길 56', '06001',
       1, 10, 96),

      ('커리팰리스', '02-1010-1117', '11:00-22:00', 'OPEN', 4.6, false, 0,
       '궁전식커리', '서울시 용산구 이태원로', '78번길 90', '04002',
       1, 10, 97),

      ('사가르', '02-1010-1118', '11:30-22:00', 'OPEN', 4.7, true, 30000,
       '남인도요리', '서울시 마포구 홍대로', '12번길 34', '04000',
       1, 10, 98),

      ('타지팰리스', '02-1010-1119', '11:00-21:30', 'OPEN', 4.5, false, 0,
       '북인도요리', '서울시 종로구 인사동길', '56번길 78', '03000',
       1, 10, 99),

      ('스파이스마켓', '02-1010-1120', '11:30-22:00', 'OPEN', 4.6, false, 0,
       '인도시장음식', '서울시 강남구 압구정로', '90번길 12', '06000',
       1, 10, 100);

-- 23. Documents for Mexican Restaurants (101-110)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/mexican/1', 'mexican_license_1.pdf', '123-45-67901'),
                                                                                    ('/documents/mexican/2', 'mexican_license_2.pdf', '123-45-67902'),
                                                                                    ('/documents/mexican/3', 'mexican_license_3.pdf', '123-45-67903'),
                                                                                    ('/documents/mexican/4', 'mexican_license_4.pdf', '123-45-67904'),
                                                                                    ('/documents/mexican/5', 'mexican_license_5.pdf', '123-45-67905'),
                                                                                    ('/documents/mexican/6', 'mexican_license_6.pdf', '123-45-67906'),
                                                                                    ('/documents/mexican/7', 'mexican_license_7.pdf', '123-45-67907'),
                                                                                    ('/documents/mexican/8', 'mexican_license_8.pdf', '123-45-67908'),
                                                                                    ('/documents/mexican/9', 'mexican_license_9.pdf', '123-45-67909'),
                                                                                    ('/documents/mexican/10', 'mexican_license_10.pdf', '123-45-67910');

-- 24. Mexican Restaurants (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('타코로코', '02-1111-1111', '11:00-22:00', 'OPEN', 4.5, false, 0,
       '타코전문점', '서울시 강남구 선릉로', '123번길 45', '06000',
       1, 11, 101),

      ('엘타코', '02-1111-1112', '11:30-22:00', 'OPEN', 4.7, false, 0,
       '멕시칸그릴', '서울시 서초구 서초대로', '67번길 89', '06001',
       1, 11, 102),

      ('부리토브라더스', '02-1111-1113', '11:00-21:30', 'OPEN', 4.4, false, 0,
       '부리토전문', '서울시 마포구 와우산로', '12번길 34', '04000',
       1, 11, 103),

      ('치포틀레', '02-1111-1114', '11:30-22:00', 'OPEN', 4.6, false, 0,
       '멕시칸그릴', '서울시 종로구 삼청로', '56번길 78', '03000',
       1, 11, 104),

      ('타코벨', '02-1111-1115', '10:30-22:00', 'OPEN', 4.3, false, 0,
       '패스트캐주얼', '서울시 강남구 도산대로', '90번길 12', '06000',
       1, 11, 105),

      ('살사리카', '02-1111-1116', '11:30-21:30', 'OPEN', 4.8, true, 20000,
       '정통멕시칸', '서울시 서초구 반포대로', '34번길 56', '06001',
       1, 11, 106),

      ('케사디아', '02-1111-1117', '11:00-22:00', 'OPEN', 4.5, false, 0,
       '케사디아전문', '서울시 용산구 이태원로', '78번길 90', '04002',
       1, 11, 107),

      ('타코스앤살사', '02-1111-1118', '11:30-22:00', 'OPEN', 4.6, false, 0,
       '수제타코', '서울시 마포구 홍대로', '12번길 34', '04000',
       1, 11, 108),

      ('라쿠카라차', '02-1111-1119', '11:00-21:30', 'OPEN', 4.7, true, 25000,
       '멕시코시티', '서울시 종로구 인사동길', '56번길 78', '03000',
       1, 11, 109),

      ('멕시칸칸티나', '02-1111-1120', '11:30-23:00', 'OPEN', 4.4, false, 0,
       '멕시칸펍', '서울시 강남구 압구정로', '90번길 12', '06000',
       1, 11, 110);

-- 25. Documents for American Restaurants (111-120)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/american/1', 'american_license_1.pdf', '123-45-67911'),
                                                                                    ('/documents/american/2', 'american_license_2.pdf', '123-45-67912'),
                                                                                    ('/documents/american/3', 'american_license_3.pdf', '123-45-67913'),
                                                                                    ('/documents/american/4', 'american_license_4.pdf', '123-45-67914'),
                                                                                    ('/documents/american/5', 'american_license_5.pdf', '123-45-67915'),
                                                                                    ('/documents/american/6', 'american_license_6.pdf', '123-45-67916'),
                                                                                    ('/documents/american/7', 'american_license_7.pdf', '123-45-67917'),
                                                                                    ('/documents/american/8', 'american_license_8.pdf', '123-45-67918'),
                                                                                    ('/documents/american/9', 'american_license_9.pdf', '123-45-67919'),
                                                                                    ('/documents/american/10', 'american_license_10.pdf', '123-45-67920');

-- 26. American Restaurants (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('버거조인트', '02-1212-1111', '11:00-22:00', 'OPEN', 4.6, false, 0,
       '수제버거', '서울시 강남구 선릉로', '123번길 45', '06000',
       1, 12, 111),

      ('스테이크하우스', '02-1212-1112', '11:30-22:00', 'OPEN', 4.8, true, 40000,
       '프리미엄스테이크', '서울시 서초구 서초대로', '67번길 89', '06001',
       1, 12, 112),

      ('치킨앤비어', '02-1212-1113', '11:00-24:00', 'OPEN', 4.5, false, 0,
       '치킨윙전문', '서울시 마포구 와우산로', '12번길 34', '04000',
       1, 12, 113),

      ('브런치카페', '02-1212-1114', '08:00-21:00', 'OPEN', 4.7, false, 0,
       '아메리칸브런치', '서울시 종로구 삼청로', '56번길 78', '03000',
       1, 12, 114),

      ('바비큐팩토리', '02-1212-1115', '11:30-22:00', 'OPEN', 4.6, true, 30000,
       '바비큐전문', '서울시 강남구 도산대로', '90번길 12', '06000',
       1, 12, 115),

      ('다이너', '02-1212-1116', '00:00-24:00', 'OPEN', 4.4, false, 0,
       '24시간다이너', '서울시 서초구 반포대로', '34번길 56', '06001',
       1, 12, 116),

      ('핫도그조인트', '02-1212-1117', '10:00-22:00', 'OPEN', 4.3, false, 0,
       '수제핫도그', '서울시 용산구 이태원로', '78번길 90', '04002',
       1, 12, 117),

      ('립하우스', '02-1212-1118', '11:30-22:00', 'OPEN', 4.7, true, 35000,
       '바비큐립', '서울시 마포구 홍대로', '12번길 34', '04000',
       1, 12, 118),

      ('샌드위치바', '02-1212-1119', '08:00-21:00', 'OPEN', 4.5, false, 0,
       '뉴욕스타일샌드위치', '서울시 종로구 인사동길', '56번길 78', '03000',
       1, 12, 119),

      ('아메리칸그릴', '02-1212-1120', '11:00-22:00', 'OPEN', 4.6, false, 0,
       '그릴요리전문', '서울시 강남구 압구정로', '90번길 12', '06000',
       1, 12, 120);

-- 27. Documents for Vietnamese Restaurants (121-130)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/vietnamese/1', 'vietnamese_license_1.pdf', '123-45-67921'),
                                                                                    ('/documents/vietnamese/2', 'vietnamese_license_2.pdf', '123-45-67922'),
                                                                                    ('/documents/vietnamese/3', 'vietnamese_license_3.pdf', '123-45-67923'),
                                                                                    ('/documents/vietnamese/4', 'vietnamese_license_4.pdf', '123-45-67924'),
                                                                                    ('/documents/vietnamese/5', 'vietnamese_license_5.pdf', '123-45-67925'),
                                                                                    ('/documents/vietnamese/6', 'vietnamese_license_6.pdf', '123-45-67926'),
                                                                                    ('/documents/vietnamese/7', 'vietnamese_license_7.pdf', '123-45-67927'),
                                                                                    ('/documents/vietnamese/8', 'vietnamese_license_8.pdf', '123-45-67928'),
                                                                                    ('/documents/vietnamese/9', 'vietnamese_license_9.pdf', '123-45-67929'),
                                                                                    ('/documents/vietnamese/10', 'vietnamese_license_10.pdf', '123-45-67930');

-- 28. Vietnamese Restaurants (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('포식당', '02-1313-1111', '11:00-22:00', 'OPEN', 4.6, false, 0,
       '쌀국수전문점', '서울시 강남구 선릉로', '123번길 45', '06000',
       1, 13, 121),

      ('반미번', '02-1313-1112', '10:30-21:30', 'OPEN', 4.5, false, 0,
       '반미전문점', '서울시 서초구 서초대로', '67번길 89', '06001',
       1, 13, 122),

      ('사이공', '02-1313-1113', '11:00-22:00', 'OPEN', 4.7, false, 0,
       '베트남음식점', '서울시 마포구 와우산로', '12번길 34', '04000',
       1, 13, 123),

      ('분짜가', '02-1313-1114', '11:30-21:30', 'OPEN', 4.4, false, 0,
       '분짜전문점', '서울시 종로구 삼청로', '56번길 78', '03000',
       1, 13, 124),

      ('하노이별', '02-1313-1115', '11:00-22:00', 'OPEN', 4.8, true, 20000,
       '하노이요리', '서울시 강남구 도산대로', '90번길 12', '06000',
       1, 13, 125),

      ('포하노이', '02-1313-1116', '10:00-22:00', 'OPEN', 4.5, false, 0,
       '하노이쌀국수', '서울시 서초구 반포대로', '34번길 56', '06001',
       1, 13, 126),

      ('반미베이커리', '02-1313-1117', '08:00-21:00', 'OPEN', 4.3, false, 0,
       '반미전문베이커리', '서울시 용산구 이태원로', '78번길 90', '04002',
       1, 13, 127),

      ('월남면옥', '02-1313-1118', '11:00-22:00', 'OPEN', 4.6, false, 0,
       '쌀국수전문', '서울시 마포구 홍대로', '12번길 34', '04000',
       1, 13, 128),

      ('포맛집', '02-1313-1119', '11:00-21:30', 'OPEN', 4.7, false, 0,
       '생면쌀국수', '서울시 종로구 인사동길', '56번길 78', '03000',
       1, 13, 129),

      ('반미인', '02-1313-1120', '10:30-21:00', 'OPEN', 4.4, false, 0,
       '베트남샌드위치', '서울시 강남구 압구정로', '90번길 12', '06000',
       1, 13, 130);

-- 29. Documents for Thai Restaurants (131-140)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/thai/1', 'thai_license_1.pdf', '123-45-67931'),
                                                                                    ('/documents/thai/2', 'thai_license_2.pdf', '123-45-67932'),
                                                                                    ('/documents/thai/3', 'thai_license_3.pdf', '123-45-67933'),
                                                                                    ('/documents/thai/4', 'thai_license_4.pdf', '123-45-67934'),
                                                                                    ('/documents/thai/5', 'thai_license_5.pdf', '123-45-67935'),
                                                                                    ('/documents/thai/6', 'thai_license_6.pdf', '123-45-67936'),
                                                                                    ('/documents/thai/7', 'thai_license_7.pdf', '123-45-67937'),
                                                                                    ('/documents/thai/8', 'thai_license_8.pdf', '123-45-67938'),
                                                                                    ('/documents/thai/9', 'thai_license_9.pdf', '123-45-67939'),
                                                                                    ('/documents/thai/10', 'thai_license_10.pdf', '123-45-67940');

-- 30. Thai Restaurants (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('방콕익스프레스', '02-1414-1111', '11:00-22:00', 'OPEN', 4.7, false, 0,
       '태국음식전문점', '서울시 강남구 선릉로', '123번길 45', '06000',
       1, 14, 131),

      ('팟타이하우스', '02-1414-1112', '11:30-22:00', 'OPEN', 4.5, false, 0,
       '팟타이전문점', '서울시 서초구 서초대로', '67번길 89', '06001',
       1, 14, 132),

      ('똠얌꿍', '02-1414-1113', '11:00-21:30', 'OPEN', 4.6, true, 20000,
       '태국식해산물', '서울시 마포구 와우산로', '12번길 34', '04000',
       1, 14, 133),

      ('타이키친', '02-1414-1114', '11:30-22:00', 'OPEN', 4.4, false, 0,
       '태국가정식', '서울시 종로구 삼청로', '56번길 78', '03000',
       1, 14, 134),

      ('방콕스트릿', '02-1414-1115', '11:00-22:00', 'OPEN', 4.8, false, 0,
       '태국길거리음식', '서울시 강남구 도산대로', '90번길 12', '06000',
       1, 14, 135),

      ('타이바질', '02-1414-1116', '11:30-21:30', 'OPEN', 4.5, false, 0,
       '태국허브요리', '서울시 서초구 반포대로', '34번길 56', '06001',
       1, 14, 136),

      ('쏨탐', '02-1414-1117', '11:00-22:00', 'OPEN', 4.6, false, 0,
       '파파야샐러드전문', '서울시 용산구 이태원로', '78번길 90', '04002',
       1, 14, 137),

      ('타이마켓', '02-1414-1118', '11:30-22:00', 'OPEN', 4.7, true, 25000,
       '태국시장음식', '서울시 마포구 홍대로', '12번길 34', '04000',
       1, 14, 138),

      ('코팡안', '02-1414-1119', '11:00-21:30', 'OPEN', 4.3, false, 0,
       '태국커리전문', '서울시 종로구 인사동길', '56번길 78', '03000',
       1, 14, 139),

      ('타이타운', '02-1414-1120', '11:30-22:00', 'OPEN', 4.5, false, 0,
       '태국음식거리', '서울시 강남구 압구정로', '90번길 12', '06000',
       1, 14, 140);

-- 31. Documents for Other Category Restaurants (141-150)
INSERT INTO rs_document (document_path, document_name, rs_document_business_id) VALUES
                                                                                    ('/documents/other/1', 'other_license_1.pdf', '123-45-67941'),
                                                                                    ('/documents/other/2', 'other_license_2.pdf', '123-45-67942'),
                                                                                    ('/documents/other/3', 'other_license_3.pdf', '123-45-67943'),
                                                                                    ('/documents/other/4', 'other_license_4.pdf', '123-45-67944'),
                                                                                    ('/documents/other/5', 'other_license_5.pdf', '123-45-67945'),
                                                                                    ('/documents/other/6', 'other_license_6.pdf', '123-45-67946'),
                                                                                    ('/documents/other/7', 'other_license_7.pdf', '123-45-67947'),
                                                                                    ('/documents/other/8', 'other_license_8.pdf', '123-45-67948'),
                                                                                    ('/documents/other/9', 'other_license_9.pdf', '123-45-67949'),
                                                                                    ('/documents/other/10', 'other_license_10.pdf', '123-45-67950');

-- 32. Other Category Restaurants (10개)
INSERT INTO rs_restaurant (
    rs_name, rs_phone, rs_time, status, rs_avg_rate,
    rs_deposit_required, rs_deposit_amount, rs_info,
    street, detail, postcode,
    owner_member_id, rs_cuisine_categories_id, rs_document_id
) VALUES
      ('러시안키친', '02-1515-1111', '11:00-22:00', 'OPEN', 4.5, false, 0,
       '러시아 전통음식', '서울시 강남구 선릉로', '123번길 45', '06000',
       1, 7, 141),

      ('터키케밥', '02-1515-1112', '11:30-22:00', 'OPEN', 4.6, false, 0,
       '터키 케밥전문', '서울시 서초구 서초대로', '67번길 89', '06001',
       1, 7, 142),

      ('그리스타베르나', '02-1515-1113', '11:00-21:30', 'OPEN', 4.4, true, 20000,
       '그리스 전통요리', '서울시 마포구 와우산로', '12번길 34', '04000',
       1, 7, 143),

      ('브라질리언그릴', '02-1515-1114', '11:30-22:00', 'OPEN', 4.7, true, 30000,
       '브라질 바베큐', '서울시 종로구 삼청로', '56번길 78', '03000',
       1, 7, 144),

      ('모로칸테이블', '02-1515-1115', '11:00-22:00', 'OPEN', 4.3, false, 0,
       '모로코 요리', '서울시 강남구 도산대로', '90번길 12', '06000',
       1, 7, 145),

      ('스페인타파스', '02-1515-1116', '11:30-23:00', 'OPEN', 4.8, true, 25000,
       '스페인 타파스', '서울시 서초구 반포대로', '34번길 56', '06001',
       1, 7, 146),

      ('아프리칸키친', '02-1515-1117', '11:00-22:00', 'OPEN', 4.2, false, 0,
       '아프리카 음식', '서울시 용산구 이태원로', '78번길 90', '04002',
       1, 7, 147),

      ('유러피안펍', '02-1515-1118', '15:00-02:00', 'OPEN', 4.5, false, 0,
       '유럽식 펍음식', '서울시 마포구 홍대로', '12번길 34', '04000',
       1, 7, 148),

      ('지중해레스토랑', '02-1515-1119', '11:00-21:30', 'OPEN', 4.6, true, 20000,
       '지중해 요리', '서울시 종로구 인사동길', '56번길 78', '03000',
       1, 7, 149),

      ('노르딕테이블', '02-1515-1120', '11:30-22:00', 'OPEN', 4.4, false, 0,
       '북유럽 요리', '서울시 강남구 압구정로', '90번길 12', '06000',
       1, 7, 150);