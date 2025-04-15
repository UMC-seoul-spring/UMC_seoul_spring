#!/bin/bash

HOOK_PATH=".git/hooks/commit-msg"

install_hook() {
  mkdir -p .git/hooks
  cat > "$HOOK_PATH" <<'EOF'
#!/bin/bash
commit_message_file=$1
commit_message=$(cat "$commit_message_file")

first_line=$(head -n 1 "$commit_message_file")
line_count=$(wc -l < "$commit_message_file")

# 첫 줄 형식 검사: 타입 + 콜론 + 공백(optional) + 메시지
if ! echo "$first_line" | grep -qE "^(feat|fix|chore|ci): ?.+$"; then
  echo "🚨 올바르지 않은 커밋 메시지 형식입니다. 🚨"
  echo "형식: <타입>:메시지 or <타입>: 메시지 (예: feat:로그인 추가 or feat: 로그인 추가)"
  exit 1
fi


# 설명 줄 확인 (최소 2줄 이상)
if [ "$line_count" -lt 2 ]; then
  echo "🚨 커밋 메시지는 두 줄 이상이어야 합니다."
  echo "- 두 번째 줄에는 '-'로 시작하는 기능 설명을 작성하세요."
  echo "예시:"
  echo "  feat: 로그인 기능 추가"
  echo "  - 카카오 OAuth 로그인 구현"
  exit 1
fi
EOF

  chmod +x "$HOOK_PATH"
  echo "✅ commit-msg 훅이 설치되었습니다."
}

remove_hook() {
  if [ -f "$HOOK_PATH" ]; then
    rm "$HOOK_PATH"
    echo "🗑️ commit-msg 훅이 제거되었습니다."
  else
    echo "ℹ️ commit-msg 훅이 존재하지 않습니다."
  fi
}

check_status() {
  if [ -x "$HOOK_PATH" ]; then
    echo "✅ commit-msg 훅이 설치되어 있습니다"
  else
    echo "❌ commit-msg 훅이 설치되어 있지 않습니다."
  fi
}

# 커맨드 분기
case "$1" in
  --remove) remove_hook ;;
  --status) check_status ;;
  *) install_hook ;;  # 기본은 설치
esac
