-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- 생성 시간: 21-10-01 14:45
-- 서버 버전: 10.4.20-MariaDB
-- PHP 버전: 7.4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `mmr`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `cinema`
--

CREATE TABLE `cinema` (
  `area_id` varchar(5) NOT NULL,
  `area` varchar(10) NOT NULL,
  `movie_id` varchar(5) NOT NULL,
  `date` date NOT NULL,
  `time` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `cinema`
--

INSERT INTO `cinema` (`area_id`, `area`, `movie_id`, `date`, `time`) VALUES
('B-01', '해운대', '00013', '2021-10-10', '17:20'),
('B-02', '사하', '00013', '2021-10-11', '11:30'),
('B-03', '수영', '00014', '2021-10-11', '12:00'),
('B-04', '동래', '00014', '2021-10-12', '13:50'),
('B-05', '금정', '00015', '2021-10-12', '18:25'),
('C-01', '제천', '00016', '2021-10-13', '15:00'),
('C-02', '청주', '00016', '2021-10-13', '20:10'),
('C-03', '충주', '00017', '2021-10-14', '12:50'),
('C-04', '괴산', '00018', '2021-10-14', '11:00'),
('C-05', '옥천', '00018', '2021-10-14', '14:20'),
('D-01', '유성', '00019', '2021-10-14', '13:00'),
('D-02', '대덕', '00019', '2021-10-14', '19:00'),
('G-01', '성남', '00004', '2021-10-04', '12:20'),
('G-02', '경기광주', '00006', '2021-10-04', '13:00'),
('G-03', '용인', '00006', '2021-10-04', '10:15'),
('G-04', '하남', '00006', '2021-10-05', '08:10'),
('G-05', '파주', '00007', '2021-10-05', '15:40'),
('G-06', '안양', '00008', '2021-10-06', '17:00'),
('G-07', '고양', '00008', '2021-10-06', '09:50'),
('G-08', '의정부', '00008', '2021-10-07', '10:30'),
('G-09', '수원', '00008', '2021-10-07', '11:00'),
('G-10', '화성', '00009', '2021-10-07', '19:00'),
('G-11', '안산', '00010', '2021-10-08', '14:45'),
('G-12', '이천', '00011', '2021-10-09', '10:00'),
('I-01', '부평', '00010', '2021-10-08', '13:10'),
('I-02', '미추홀', '00011', '2021-10-08', '18:40'),
('I-03', '계양', '00011', '2021-10-09', '16:50'),
('I-04', '연수', '00012', '2021-10-10', '08:30'),
('I-05', '남동', '00012', '2021-10-10', '12:30'),
('J-01', '제주', '00015', '2021-10-12', '21:50'),
('J-02', '서귀포', '00016', '2021-10-13', '13:20'),
('S-01', '강남', '00001', '2021-10-01', '10:20'),
('S-02', '잠실', '00001', '2021-10-01', '17:30'),
('S-03', '송파', '00002', '2021-10-02', '09:20'),
('S-04', '동대문', '00002', '2021-10-02', '11:00'),
('S-05', '영등포', '00003', '2021-10-02', '21:50'),
('S-06', '강서', '00004', '2021-10-03', '13:40'),
('S-07', '광진', '00001', '2021-10-03', '16:30'),
('S-08', '종로', '00005', '2021-10-03', '11:20'),
('SJ-01', '보람', '00021', '2021-10-15', '16:00'),
('SJ-02', '아름', '00021', '2021-10-15', '20:50'),
('SJ-03', '한솔', '00022', '2021-10-15', '21:10'),
('U-01', '울주', '00020', '2021-10-15', '09:30');

-- --------------------------------------------------------

--
-- 테이블 구조 `inquiry_list`
--

CREATE TABLE `inquiry_list` (
  `inquiry_id` int(11) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `movie_id` varchar(5) NOT NULL,
  `area_id` varchar(5) NOT NULL,
  `date` date NOT NULL,
  `time` varchar(5) NOT NULL,
  `seat` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 테이블 구조 `member`
--

CREATE TABLE `member` (
  `name` varchar(10) NOT NULL,
  `date_of_birth` date NOT NULL,
  `phone_number` varchar(11) NOT NULL,
  `id` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `member`
--

INSERT INTO `member` (`name`, `date_of_birth`, `phone_number`, `id`, `password`) VALUES
('정아영', '2005-03-23', '01025869419', 'monayoung0323', 'ahddl0628@!');

-- --------------------------------------------------------

--
-- 테이블 구조 `movie`
--

CREATE TABLE `movie` (
  `movie_id` varchar(5) NOT NULL,
  `title` varchar(30) NOT NULL,
  `genre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `movie`
--

INSERT INTO `movie` (`movie_id`, `title`, `genre`) VALUES
('00001', '모가디슈', '액션'),
('00002', '블랙 위도우', '액션'),
('00003', '분노의 질주: 더 얼티메이트', '액션'),
('00004', '싱크홀', '드라마'),
('00005', '극장판 귀멸의 칼날: 무한열차편', '애니메이션'),
('00006', '소울', '애니메이션'),
('00007', '크루엘라', '드라마'),
('00008', '샹치와 텐 링즈의 전설', '액션'),
('00009', '인질', '액션'),
('00010', '미나리', '드라마'),
('00011', '발신제한', '드라마'),
('00012', '보이스', '범죄'),
('00013', '보스 베이비 2', '애니메이션'),
('00014', '콰이어트 플레이스 2', '스릴러'),
('00015', '랑종', '공포'),
('00016', '컨저링 3: 악마가 시켰다', '공포'),
('00017', '고질라 VS. 콩', '액션'),
('00018', '미션 파서블', '코미디'),
('00019', '기적', '드라마'),
('00020', '더 수어사이드 스쿼드', '액션'),
('00021', '비와 당신의 이야기', '드라마'),
('00022', '서복', '드라마');

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `cinema`
--
ALTER TABLE `cinema`
  ADD PRIMARY KEY (`area_id`),
  ADD KEY `link_movie_id` (`movie_id`) USING BTREE;

--
-- 테이블의 인덱스 `inquiry_list`
--
ALTER TABLE `inquiry_list`
  ADD PRIMARY KEY (`inquiry_id`);

--
-- 테이블의 인덱스 `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`);

--
-- 테이블의 인덱스 `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`movie_id`);

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `inquiry_list`
--
ALTER TABLE `inquiry_list`
  MODIFY `inquiry_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- 덤프된 테이블의 제약사항
--

--
-- 테이블의 제약사항 `cinema`
--
ALTER TABLE `cinema`
  ADD CONSTRAINT `link_movie_id` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
